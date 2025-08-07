package ticketingApp;
import java.awt.Color;
import java.awt.Font;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AccountInfoPanel extends JPanel{

    private static final long serialVersionUID = 1L;
    private JPasswordField pwdPassword;
    private JTextField totalField;
    private JTextField phoneField;
    private JTextField nameField;
    private JTextField emailField;

	public AccountInfoPanel(User user){
		
		// TODO remove testing data and implement real data
		Venue testVenue = new Venue("MSG", 100);
		Venue testVenue2 = new Venue("City Field", 200);
		Venue testVenue3 = new Venue("Metlife", 200);		
		Event testEvent = new Event("Super Cool event", testVenue, 100, new Date());
		Event testEvent2 = new Event("mets vs. braves", testVenue2, 100, new Date());
		Event testEvent3 = new Event("giants vs tampa bay", testVenue3, 100, new Date());
		Event testEvent4 = new Event("Chicago", testVenue, 100, new Date());
		Event testEvent5 = new Event("mets vs yankees", testVenue2, 100, new Date());
		Event testEvent6 = new Event("Mumford and Sons", testVenue3, 100, new Date());		
		Ticket[] testTickets = { new Ticket(testEvent, 21.1),
				new Ticket(testEvent2, 15.1),
				new Ticket(testEvent5, 105.8),
				new Ticket(testEvent3, 13)};	
		Ticket[] testUpcomingTickets = { new Ticket(testEvent4, 210.99),
				new Ticket(testEvent5, 105.8),
				new Ticket(testEvent, 400),
				new Ticket(testEvent6, 19)};
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
        nameField.setText("Guest Name");
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
        phoneField.setText("6461234567");
        phoneField.setBounds(262, 85, 337, 26);
        add(phoneField);
        phoneField.setColumns(10);
        
        // Email information on panel
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        emailLabel.setBounds(169, 119, 81, 19);
        add(emailLabel);
        
        emailField = new JTextField();
        emailField.setText("guestemail@nyu.edu");
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
        pwdPassword.setText("password123");
        pwdPassword.setBounds(262, 152, 337, 26);
        add(pwdPassword);
        
        // Upcoming event information on panel
        JLabel upcomingLabel = new JLabel("Upcoming Events:");
        upcomingLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        upcomingLabel.setBounds(169, 223, 146, 19);
        add(upcomingLabel);
        
        JScrollPane upcomingScrollPane = new JScrollPane();
        upcomingScrollPane.setBounds(169, 247, 539, 133);
        this.add(upcomingScrollPane);
        
        this.ticketTable(upcomingScrollPane, testUpcomingTickets, new Color(240, 255, 240));      
        
        // Past event information on panel
        JLabel pastEventsLabel = new JLabel("Past Events:");
        pastEventsLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        pastEventsLabel.setBounds(169, 392, 146, 19);
        add(pastEventsLabel);
        
        JScrollPane pastScrollPane = new JScrollPane();
        pastScrollPane.setBounds(169, 414, 539, 99);
        add(pastScrollPane);
        
        this.ticketTable(pastScrollPane, testTickets, new Color(255, 228, 225)); 
        
        // Total spent information on panel
        JLabel totalSpentLabel = new JLabel("Total Spent: ");
        totalSpentLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        totalSpentLabel.setBounds(169, 192, 97, 19);
        add(totalSpentLabel);
        
        totalField = new JTextField();
        totalField.setEditable(false);
        totalField.setText("$500.89");
        totalField.setBounds(272, 185, 327, 26);
        add(totalField);
        totalField.setColumns(10);
        
        // Button information on panel
        JButton logoutBtn = new JButton("Logout");
        logoutBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        logoutBtn.setBounds(591, 525, 117, 29);
        add(logoutBtn);
        
        JButton browseBtn = new JButton("Browse Events");
        browseBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        browseBtn.setBounds(169, 525, 117, 29);
        add(browseBtn);
        
        JButton sellBtn = new JButton("Sell");
        sellBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        sellBtn.setBounds(373, 525, 117, 29);
        add(sellBtn);
        
        JButton editBtn = new JButton("Edit");
        editBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
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
	public void ticketTable(JScrollPane pane, Ticket[] tickets, Color bgColor) {
		JTable table = new JTable();
        pane.setViewportView(table);
        table.setEnabled(false);
		Object[] columns = {"Event", "Time", "Venue"
		, "Cost"};
		DefaultTableModel model = new DefaultTableModel();
		
		model.setColumnIdentifiers(columns);
		table.setModel(model);
		
		table.setBackground(bgColor);
		table.setForeground(Color.black);
		table.setSelectionBackground(Color.white);
		table.setGridColor(new Color(0, 0, 51));
		table.setSelectionForeground(Color.white);
		table.setFont(new Font("Tahoma", Font.PLAIN, 10));
		table.setRowHeight(30);
		table.setAutoCreateRowSorter(true);
		
		for (int i = 0; i < tickets.length; i++) {
			model.addRow(getRowInfo(tickets[i]));
		}
	}
	
	/**
	 * This function will create an object with row information for the table
	 * @param toAdd
	 * @return
	 */
	public static Object[] getRowInfo(Ticket toAdd) {
		Object[] obj = new Object[7];
		obj[0] = toAdd.getEvent().getName();
		obj[1] = toAdd.getTime();
		obj[2] = toAdd.getVenue().getName();
		obj[3] = toAdd.getCost();
		return obj;
	}
}