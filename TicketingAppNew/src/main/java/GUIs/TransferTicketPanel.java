package GUIs;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import data.DataStore;
import data.dto.TicketDto;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class TransferTicketPanel extends JPanel{

    private static final long serialVersionUID = 1L;
    private JTextField UserNameField;
	private JTextField UserEmailField;
	
	private static JTextPane EventNameTextPane;
	private static JTextPane VenueNameTextPane;
	private static JTextPane TicketPriceTextPane;
	private static JLabel ticketAgeLbl;
    private static long curTicketId;

	private JButton TransferButton;
	private JButton CancelButton;
	
	private JLabel TitleLabel;
	private JLabel TicketEventLabel;
	private JLabel TicketVenueLabel;
	private JLabel TicketPriceLabel;
	private JLabel UserNameLabel;
	private JLabel UserEmailLabel;
	
	
	public TransferTicketPanel(){

		TransferButton = new JButton("Transfer to this user");
		TransferButton.addActionListener(e -> {
            if (DataStore.checkValidTransfer(UserNameField.getText(), UserEmailField.getText())) {
                DataStore.transferTicket(EventNameTextPane.getText(), curTicketId, UserEmailField.getText());
                AccountInfoPanel.loadTables();
                MainFrame.swap("userinfo");
            } else {
                System.out.println("invalid entry");
            }
        });
		TransferButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		TitleLabel = new JLabel("Transfering Ticket");
		TitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		TitleLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		TicketEventLabel = new JLabel("Event Name");
		TicketEventLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		TicketVenueLabel = new JLabel("Venue Name");
		TicketVenueLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		TicketPriceLabel = new JLabel("Ticket Price");
		TicketPriceLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		EventNameTextPane = new JTextPane();
		
		VenueNameTextPane = new JTextPane();
				
		TicketPriceTextPane = new JTextPane();

        EventNameTextPane.setEditable(false);
        EventNameTextPane.setFocusable(false);

        VenueNameTextPane.setEditable(false);
        VenueNameTextPane.setFocusable(false);

        TicketPriceTextPane.setEditable(false);
        TicketPriceTextPane.setFocusable(false);

        String eventName = EventNameTextPane.getText();

		UserNameLabel = new JLabel("User Name");
		UserNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		UserEmailLabel = new JLabel("User Email");
		UserEmailLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		UserNameField = new JTextField();
		UserNameField.setColumns(10);
		
		UserEmailField = new JTextField();
		UserEmailField.setColumns(10);
		
		CancelButton = new JButton("Cancel");
		CancelButton.addActionListener(e -> MainFrame.swap("userinfo"));
		CancelButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		ticketAgeLbl = new JLabel();
		ticketAgeLbl.setHorizontalAlignment(SwingConstants.CENTER);
		ticketAgeLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(299, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(CancelButton, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
						.addComponent(TransferButton))
					.addGap(325))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(126)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(TicketPriceLabel, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
							.addGap(36)
							.addComponent(TicketPriceTextPane, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(UserNameLabel, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
								.addGap(36)
								.addComponent(UserNameField))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(TicketEventLabel, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(EventNameTextPane, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(TicketVenueLabel, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
								.addGap(26)
								.addComponent(VenueNameTextPane, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(UserEmailLabel, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
								.addGap(36)
								.addComponent(UserEmailField, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE))))
					.addGap(356))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(312)
					.addComponent(ticketAgeLbl, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(312, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(312)
					.addComponent(TitleLabel, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(312, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(TitleLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(ticketAgeLbl, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(TicketEventLabel)
						.addComponent(EventNameTextPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(TicketVenueLabel)
						.addComponent(VenueNameTextPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(TicketPriceTextPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(TicketPriceLabel))
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(UserNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(UserNameLabel, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(UserEmailLabel, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(UserEmailField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(64)
					.addComponent(TransferButton)
					.addGap(30)
					.addComponent(CancelButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(148, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

    }
	
	public static void loadTicketInfo(TicketDto ticket) {
		if (ticket != null) {
			EventNameTextPane.setText(ticket.getEventName());
			VenueNameTextPane.setText(DataStore.findEventByName(ticket.getEventName()).getVenue());
			TicketPriceTextPane.setText(Double.toString(ticket.getPrice()));
            curTicketId = ticket.getTicketId();
            ticketAgeLbl.setText("Ticket age: " + ticket.getAgeType());
		}
	}
}