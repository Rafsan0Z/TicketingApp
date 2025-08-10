package GUIs;
import java.awt.Color;
import java.awt.Font;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import data.DataStore;
import data.dto.TicketDto;
import data.dto.UserDto;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JCheckBox;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class AccountInfoPanel extends JPanel{

    private static final long serialVersionUID = 1L;
    private static JPasswordField pwdPassword;
    private static JTextField totalField;
    private static JTextField phoneField;
    private static JTextField nameField;
    private static JTextField emailField;
    private static DefaultTableModel model;
    private boolean editMode = false;
    
    private static UserDto user;
    private TicketDto ticketSelected = null;

    public AccountInfoPanel(){

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

        // Upcoming event information on panel
        JLabel upcomingLabel = new JLabel("Upcoming Events:");
        upcomingLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        upcomingLabel.setBounds(169, 223, 146, 19);
        add(upcomingLabel);

        // Past event information on panel
        JLabel pastEventsLabel = new JLabel("Past Events:");
        pastEventsLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        pastEventsLabel.setBounds(169, 392, 146, 19);
        add(pastEventsLabel);

        // Total spent information on panel
        JLabel totalSpentLabel = new JLabel("Total Spent: ");
        totalSpentLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        totalSpentLabel.setBounds(169, 192, 97, 19);
        add(totalSpentLabel);

        totalField = new JTextField();
        totalField.setEditable(false);
        totalField.setBounds(272, 185, 327, 26);
        add(totalField);
        totalField.setColumns(10);

        // Button information on panel
        JButton logoutBtn = new JButton("Logout");
        logoutBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	MainFrame.swap("login");
        		DataStore.setCurrentUser();
            }
        });
        logoutBtn.setBounds(591, 525, 117, 29);
        add(logoutBtn);

        JButton browseBtn = new JButton("Browse Events");
        browseBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		MainFrame.swap("eventview");
        	}
        });
        browseBtn.setBounds(169, 525, 117, 29);
        add(browseBtn);

        JButton sellBtn = new JButton("Sell");
        sellBtn.addActionListener(new ActionListener() {

        	public void actionPerformed(ActionEvent e) {
        		MainFrame.swap("transfer");
        	}
        });
        sellBtn.setBounds(373, 525, 117, 29);
        add(sellBtn);

        JButton editBtn = new JButton("Edit");
        editBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (editMode) {
            		pwdPassword.setEditable(false);
            		phoneField.setEditable(false);
            		emailField.setEditable(false);
            		nameField.setEditable(false);
            		editBtn.setText("Edit");
            		String newPassword = new String(pwdPassword.getPassword());
            		user.updateUser(nameField.getText(), phoneField.getText(), emailField.getText(), newPassword);
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
        
    }

    /**
     * This method will format and popup a table with ticket information on a scrollable pane
     * @param pane
     * @param tickets
     * @param bgColor
     */
    public void ticketTable(JScrollPane pane, TicketDto[] tickets, Color bgColor, boolean selectable) {
        JTable table = new JTable();
        pane.setViewportView(table);
        Object[] columns = {"Event", "Time", "Venue"
                , "Cost"};
        model = new DefaultTableModel();

        model.setColumnIdentifiers(columns);
        table.setModel(model);

        table.setBackground(bgColor);
        table.setForeground(Color.black);
        table.setGridColor(new Color(0, 0, 51));
        table.setFont(new Font("Tahoma", Font.PLAIN, 10));
        table.setRowHeight(30);
        table.setAutoCreateRowSorter(true);

        if (selectable) {
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            table.setSelectionBackground(new Color(143, 188, 143));
            table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

				@Override
				public void valueChanged(ListSelectionEvent e) {
					// TODO Auto-generated method stub
					int rowSelected = table.getSelectedRow();
					ticketSelected = getSelectedTicket(tickets, rowSelected);
				}
            });
            
        } else {
            table.setEnabled(false);
        }

        for (int i = 0; i < tickets.length; i++) {
            model.addRow(getRowInfo(tickets[i]));
        }
    }
    
    public static void loadUser() {
    	user = DataStore.getCurrentUser();
    	pwdPassword.setText(user.getPassword());
        totalField.setText(Double.toString(user.calculateTotal()));
        phoneField.setText(user.getPhone());
        nameField.setText(user.getName());
        emailField.setText(user.getEmail());
    	
    }
    
    public static void loadTables() {
    	TicketDto[] pastTickets = user.getPassedTickets();
//    	TicketDto[] upcomingTickets = user.getUpcomingTickets();
      TicketDto[] upcomingTickets = { new TicketDto("mets vs. braves", 210.99, true, "so@user.com"),
      new TicketDto("mets vs yankees", 105.8, true, "so@user.com"),
      new TicketDto("Mumford and sons", 400, true, "so@user.com"),
      new TicketDto("Super Cool event", 19, true, "so@user.com")};
        
    	JScrollPane upcomingScrollPane = new JScrollPane();
        upcomingScrollPane.setBounds(169, 247, 539, 133);
        MainFrame.userinfoPanel.add(upcomingScrollPane);

        MainFrame.userinfoPanel.ticketTable(upcomingScrollPane, upcomingTickets, new Color(240, 255, 240), true);
        
        JScrollPane pastScrollPane = new JScrollPane();
        pastScrollPane.setBounds(169, 414, 539, 99);
        MainFrame.userinfoPanel.add(pastScrollPane);

        MainFrame.userinfoPanel.ticketTable(pastScrollPane, pastTickets, new Color(255, 228, 225), false);
        
    }

    /**
     * This function will create an object with row information for the table
     * @param toAdd
     * @return
     */
    public static Object[] getRowInfo(TicketDto toAdd) {
        Object[] obj = new Object[7];
        obj[0] = toAdd.getEvent();
        obj[1] = new Date();
        obj[2] = "MSG";
        obj[3] = toAdd.getPrice();
        return obj;
    }
    
    public TicketDto getSelectedTicket(TicketDto[] tickets, int rowSelected) {
    	if (rowSelected != -1) {
    		return tickets[rowSelected];
    	}
    	return null;
    }
}