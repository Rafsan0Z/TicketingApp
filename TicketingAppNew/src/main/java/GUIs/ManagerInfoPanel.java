package GUIs;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import data.DataStore;
import data.dto.EventDto;
import data.dto.ManagerDto;

public class ManagerInfoPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static JTextField nameField;
	private static JTextField phoneField;
	private static JTextField emailField;
	private static JPasswordField pwdPassword;
	private static DefaultTableModel model;
	
	private static ManagerDto manager;
	private EventDto eventSelected;

	/**
	 * Create the panel.
	 */
	public ManagerInfoPanel() {
		setLayout(null);

        // Title information on panel
        JLabel titleLabel = new JLabel("Welcome");
        titleLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(234, 6, 332, 58);
        add(titleLabel);

        // Name information on panel
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        nameLabel.setBounds(169, 57, 81, 19);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setEditable(false);
        nameField.setOpaque(true);
        nameField.setBounds(262, 54, 332, 26);
        add(nameField);
        nameField.setColumns(10);

        // Phone information on panel
        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        phoneLabel.setBounds(169, 88, 81, 19);
        add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setEditable(false);
        phoneField.setBounds(262, 85, 337, 26);
        add(phoneField);
        phoneField.setColumns(10);

        // Email information on panel
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        emailLabel.setBounds(169, 119, 81, 19);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setEditable(false);
        emailField.setColumns(10);
        emailField.setBounds(262, 116, 337, 26);
        add(emailField);

        // Password information on panel
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        passwordLabel.setBounds(169, 155, 81, 19);
        add(passwordLabel);

        pwdPassword = new JPasswordField();
        pwdPassword.setEditable(false);
        pwdPassword.setEchoChar('*');
        pwdPassword.setBounds(262, 152, 337, 26);
        add(pwdPassword);
        
        JCheckBox showPassword = new JCheckBox("Show");
        showPassword.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (showPassword.isSelected()) {
        			pwdPassword.setEchoChar((char)0);
        		} else {
        			pwdPassword.setEchoChar('*');
        		}
        	}
        });
        showPassword.setBounds(611, 153, 81, 23);
        add(showPassword);

        // Past event information on panel
        JLabel allEventsLabel = new JLabel("All Events:");
        allEventsLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        allEventsLabel.setBounds(169, 186, 146, 19);
        add(allEventsLabel);

        // Button information on panel
        JButton logoutBtn = new JButton("Logout");
        logoutBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	MainFrame.swap("login");
        		DataStore.logoutCurrentManager();
            }
        });
        logoutBtn.setBounds(591, 525, 117, 29);
        add(logoutBtn);


        JButton editBtn = new JButton("Edit");
        editBtn.addActionListener(new ActionListener() {
            private boolean editMode;

			public void actionPerformed(ActionEvent e) {
            	if (editMode) {
            		pwdPassword.setEditable(false);
            		phoneField.setEditable(false);
            		emailField.setEditable(false);
            		nameField.setEditable(false);
            		editBtn.setText("Edit");
            		String newPassword = new String(pwdPassword.getPassword());
            		manager.updateManager(nameField.getText(), phoneField.getText(), emailField.getText(), newPassword);
            		editMode = false;
            	} else {
            		pwdPassword.setEditable(true);
            		phoneField.setEditable(true);
            		emailField.setEditable(true);
            		nameField.setEditable(true);
            		editBtn.setText("Save");
            		editMode = true;
            	}
            }
        });
        editBtn.setBounds(611, 54, 117, 29);
        add(editBtn);
        
        EventDto[] events = DataStore.getEvents();
        JScrollPane eventScrollPane = new JScrollPane();
        eventScrollPane.setBounds(169, 217, 539, 296);
        this.add(eventScrollPane);

        this.eventTable(eventScrollPane, events, new Color(240, 255, 240));
        
        JButton addBtn = new JButton("Add Event");
        addBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		MainFrame.swap("addevent");
        	}
        });
        addBtn.setBounds(468, 525, 117, 29);
        add(addBtn);
        
	}
	
	/**
     * This method will format and popup a table with ticket information on a scrollable pane
     * @param pane
     * @param tickets
     * @param bgColor
     */
    public void eventTable(JScrollPane pane, EventDto[] events, Color bgColor) {
    	System.out.println(events.length);
        JTable table = new JTable();
        pane.setViewportView(table);
        Object[] columns = {"Event", "Time", "Venue"
                , "Cost", "Status"};
        model = new DefaultTableModel();

        model.setColumnIdentifiers(columns);
        table.setModel(model);

        table.setBackground(bgColor);
        table.setForeground(Color.black);
        table.setGridColor(new Color(0, 0, 51));
        table.setFont(new Font("Tahoma", Font.PLAIN, 10));
        table.setRowHeight(30);
        table.setAutoCreateRowSorter(true);

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


        for (int i = 0; i < events.length; i++) {
            model.addRow(getRowInfo(events[i]));
        }
    }
	
    public static void loadManager() {
    	manager = DataStore.getCurrentManager();
    	pwdPassword.setText(manager.getPassword());
        phoneField.setText(manager.getPhone());
        nameField.setText(manager.getName());
        emailField.setText(manager.getEmail());
    	
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
        if (toAdd.isSoldOut()) {
        	obj[4] = "Sold Out";
        } else {
        	obj[4] = "Available";
        }
        return obj;
    }
    
    public EventDto getSelectedEvent(EventDto[] events, int rowSelected) {
    	if (rowSelected != -1) {
    		return events[rowSelected];
    	}
    	return null;
    }
}

