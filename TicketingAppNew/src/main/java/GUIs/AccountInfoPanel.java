package GUIs;
import java.awt.Color;
import java.awt.Font;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import data.dto.EventDto;
import data.dto.TicketDto;
import data.dto.VenueDto;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class AccountInfoPanel extends JPanel{

    private static final long serialVersionUID = 1L;
    private JPasswordField pwdPassword;
    private JTextField totalField;
    private JTextField phoneField;
    private JTextField nameField;
    private JTextField emailField;

    private int rowSelected = -1;

    public AccountInfoPanel(){

        // TODO remove testing data and implement real data
//        VenueDto testVenue = new VenueDto("MSG", 100);
//        VenueDto testVenue2 = new VenueDto("City Field", 200);
//        VenueDto testVenue3 = new VenueDto("Metlife", 200);
//        EventDto testEvent = new EventDto("Super Cool event", 100, 100, "MSG", new Date());
//        EventDto testEvent2 = new EventDto("mets vs. braves", 200, 200, "City Field", new Date());
//        EventDto testEvent3 = new EventDto("giants vs tampa bay", 100, 100, "Metlife", new Date());
//        EventDto testEvent4 = new EventDto("Chicago", 100, 100, "MSG", new Date());
//        EventDto testEvent5 = new EventDto("mets vs yankees", 100, 100, "City Field", new Date());
//        EventDto testEvent6 = new EventDto("Mumford and Sons", 300, 100, "Metlife", new Date());
        TicketDto[] testTickets = { new TicketDto("Super Cool event", 21.1, true, "so@user.com"),
                new TicketDto("mets vs. braves", 15.1, true, "so@user.com"),
                new TicketDto("giants vs tampa bay", 105.8, true, "so@user.com"),
                new TicketDto("Chicago", 13, true, "so@user.com")};
        TicketDto[] testUpcomingTickets = { new TicketDto("mets vs. braves", 210.99, true, "so@user.com"),
                new TicketDto("mets vs yankees", 105.8, true, "so@user.com"),
                new TicketDto("Mumford and sons", 400, true, "so@user.com"),
                new TicketDto("Super Cool event", 19, true, "so@user.com")};
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

        this.ticketTable(upcomingScrollPane, testUpcomingTickets, new Color(240, 255, 240), true);

        // Past event information on panel
        JLabel pastEventsLabel = new JLabel("Past Events:");
        pastEventsLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        pastEventsLabel.setBounds(169, 392, 146, 19);
        add(pastEventsLabel);

        JScrollPane pastScrollPane = new JScrollPane();
        pastScrollPane.setBounds(169, 414, 539, 99);
        add(pastScrollPane);

        this.ticketTable(pastScrollPane, testTickets, new Color(255, 228, 225), false);

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
    public void ticketTable(JScrollPane pane, TicketDto[] tickets, Color bgColor, boolean selectable) {
        JTable table = new JTable();
        pane.setViewportView(table);
        Object[] columns = {"Event", "Time", "Venue"
                , "Cost"};
        DefaultTableModel model = new DefaultTableModel();

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
            rowSelected = table.getSelectedRow();
        } else {
            table.setEnabled(false);
        }

        for (int i = 0; i < tickets.length; i++) {
            model.addRow(getRowInfo(tickets[i]));
        }
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

    public int getSelectedRow() {
        return rowSelected;
    }
}