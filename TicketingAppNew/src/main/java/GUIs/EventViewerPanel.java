package GUIs;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import data.DataStore;
import data.dto.EventDto;

public class EventViewerPanel extends JPanel{

    private static final long serialVersionUID = 1L;
    private JTable table;
    private JLabel TopicLabel;
    private JButton PurchaseButton;
    private JButton AccountButton;
    private EventDto eventSelected;
    DataStore.Role r = DataStore.getCurrentRole();

    public EventViewerPanel(){
        addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override public void componentShown(java.awt.event.ComponentEvent e) {
                updateRoleVisibility();
            }
        });

		EventDto[] events = DataStore.getAvailableEvents();


		TopicLabel = new JLabel("View all Available Events");
		TopicLabel.setHorizontalAlignment(SwingConstants.CENTER);
		TopicLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));

		table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setSelectionBackground(new Color(143, 188, 143));
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				int rowSelected = table.getSelectedRow();
				eventSelected = getSelectedEvent(events, rowSelected);
			}
        });

        Object[] columns = {"Event", "Time", "Venue"
                , "Cost"};
        DefaultTableModel model = new DefaultTableModel(){
            private static final long serialVersionUID = 1L;

			@Override
            public boolean isCellEditable(int row, int column) {
                return false; // completely read only
            };
        };

        model.setColumnIdentifiers(columns);
        table.setModel(model);

        table.setBackground( new Color(240, 255, 240));
        table.setForeground(Color.black);
        table.setGridColor(new Color(0, 0, 51));
        table.setFont(new Font("Tahoma", Font.PLAIN, 10));
        table.setRowHeight(30);
        table.setAutoCreateRowSorter(true);

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
							.addComponent(table, GroupLayout.PREFERRED_SIZE, 753, GroupLayout.PREFERRED_SIZE)))
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
					.addComponent(table, GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
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
}