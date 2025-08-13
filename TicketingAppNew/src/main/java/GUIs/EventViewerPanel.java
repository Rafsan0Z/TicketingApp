package GUIs;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import data.DataStore;
import data.dto.EventDto;

public class EventViewerPanel extends JPanel{

    private static final long serialVersionUID = 1L;
    private JTable table;
    private JLabel TopicLabel;
    private JButton PurchaseButton;
    private JButton AccountButton;
    private EventDto eventSelected;
    private static EventDto[] events;
    private static DefaultTableModel model;
    DataStore.Role r = DataStore.getCurrentRole();

    public EventViewerPanel(){
        addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override public void componentShown(java.awt.event.ComponentEvent e) {
                updateRoleVisibility();
            }
        });

		events = DataStore.getAvailableEvents();


		TopicLabel = new JLabel("View all Available Events");
		TopicLabel.setHorizontalAlignment(SwingConstants.CENTER);
		TopicLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		

		table = new JTable();
		Font tableFont = new Font("SansSerif", Font.PLAIN, 16);
		table.setFont(tableFont);
		table.getTableHeader().setFont(tableFont);
		table.getTableHeader().setFont(tableFont);
		
		JScrollPane pane = new JScrollPane(table);
		
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setSelectionBackground(new Color(143, 188, 143));
        table.getSelectionModel().addListSelectionListener(e -> {
            int rowSelected = table.getSelectedRow();
            eventSelected = getSelectedEvent(events, rowSelected);
        });
        

        Object[] columns = {"Event", "Time", "Venue"
                , "Cost"};
        model = new DefaultTableModel(){
            private static final long serialVersionUID = 1L;

			@Override
            public boolean isCellEditable(int row, int column) {
                return false; // completely read only
            };
            
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 1: // Time column
                        return Date.class;
                    case 3: // Cost column
                        return Double.class;
                    default: // All other columns are treated as Strings
                        return String.class;
                }
            }
        };
        
        model.setColumnIdentifiers(columns);
        table.setModel(model);

        table.setBackground( new Color(240, 255, 240));
        table.setForeground(Color.black);
        table.setGridColor(new Color(0, 0, 51));
        table.setFont(new Font("Tahoma", Font.PLAIN, 10));
        table.setRowHeight(30);
        table.setAutoCreateRowSorter(true);
        
        // Formatting date for easy sorting
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM YYY dd HH:mm:ss");
        table.getColumnModel().getColumn(1).setCellRenderer((table1, value, isSelected, hasFocus, row, column) -> {
        	JLabel label = new JLabel(formatter.format((Date) value));
        	label.setFont(new Font("Tahoma", Font.PLAIN, 10));
        	label.setOpaque(true);
        	label.setBackground(isSelected ? table1.getSelectionBackground() : table1.getBackground());
        	label.setForeground(isSelected ? table1.getSelectionForeground() : table1.getForeground());
        	return label;
        });
        
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);
        
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING)); // Primary sort
        sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING)); // Secondary sort
        sortKeys.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
        sortKeys.add(new RowSorter.SortKey(3, SortOrder.ASCENDING));

		for (int i = 0; i < events.length; i++) {
            model.addRow(getRowInfo(events[i]));
        }

		PurchaseButton = new JButton("Purchase Tickets");
        PurchaseButton.setVisible(r == DataStore.Role.USER);
        PurchaseButton.addActionListener((ActionEvent e) -> {
            if (eventSelected != null) {
                PurchaseConfirmation pc = new PurchaseConfirmation(eventSelected.getEventName());
                pc.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(EventViewerPanel.this, "Please select an Event");
            }
        });
		PurchaseButton.setFont(new Font("Tahoma", Font.PLAIN, 15));

		AccountButton = new JButton("Account Info");
        AccountButton.setVisible(r == DataStore.Role.USER || r == DataStore.Role.MANAGER);
		AccountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.swap("userinfo");
			}
		});

		AccountButton.setFont(new Font("Tahoma", Font.PLAIN, 15));

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(TopicLabel, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE)
							.addGap(129)
							.addComponent(AccountButton, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(317)
							.addComponent(PurchaseButton)
							.addPreferredGap(ComponentPlacement.RELATED, 193, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(23)
							.addComponent(pane, GroupLayout.PREFERRED_SIZE, 753, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(24, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(23)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(AccountButton, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(TopicLabel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(pane, GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(PurchaseButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(40))
		);
		setLayout(groupLayout);
    }

	/**
     * This function will create an object with row information for the table
     * @param toAdd
     * @return
     */
    public static Object[] getRowInfo(EventDto toAdd) {
        Object[] obj = new Object[7];
        obj[0] = toAdd.getEventName();
        obj[1] = toAdd.getDate();
        obj[2] = toAdd.getVenue();
        obj[3] = toAdd.getCost();
        return obj;
    }

    public EventDto getSelectedEvent(EventDto[] events, int rowSelected) {
    	if (rowSelected != -1) {
    		return events[rowSelected];
    	}
    	return null;
    }
    private void updateRoleVisibility() {
        DataStore.Role r = DataStore.getCurrentRole();
        PurchaseButton.setVisible(r == DataStore.Role.USER);
        AccountButton.setVisible(r == DataStore.Role.USER || r == DataStore.Role.MANAGER);
        revalidate();
        repaint();
    }
    
    public static void refreshEvents() {
    	events = DataStore.getAvailableEvents();
    	model.setRowCount(0);
    	for (int i = 0; i < events.length; i++) {
            model.addRow(getRowInfo(events[i]));
        }
    }
}