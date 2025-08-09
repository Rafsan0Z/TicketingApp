package GUIs;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import MainFrame;

public class TicketPurchasePanel extends JPanel{

    private static final long serialVersionUID = 1L;
    JLabel PurchaseTicketsLabel;
    JLabel TicketEventLabel;
    JLabel TicketVenueLabel;
    JLabel TicketPriceLabel;
    JLabel TicketManagerLabel;
    JLabel SelectAgeLabel;
    JLabel NumTicketsLabel;
    JLabel TotalChargeLabel;
    
    JButton CancelButton;
    JButton PurchaseButton;

	public TicketPurchasePanel(){

		PurchaseTicketsLabel = new JLabel("Purchase Tickets");
		PurchaseTicketsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		PurchaseTicketsLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		TicketEventLabel = new JLabel("Event Name");
		TicketEventLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		TicketVenueLabel = new JLabel("Venue Name");
		TicketVenueLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		TicketPriceLabel = new JLabel("Ticket Price");
		TicketPriceLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		TicketManagerLabel = new JLabel("Managed By");
		TicketManagerLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		SelectAgeLabel = new JLabel("Select Age");
		SelectAgeLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		NumTicketsLabel = new JLabel("Number of Tickets");
		NumTicketsLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		TotalChargeLabel = new JLabel("Total Charge");
		TotalChargeLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		CancelButton = new JButton("Cancel");
		CancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.swap("eventview");
			}
		});
		CancelButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		PurchaseButton = new JButton("Purchase these tickets");
		PurchaseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		PurchaseButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(73)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(TotalChargeLabel, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
						.addComponent(NumTicketsLabel, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
						.addComponent(SelectAgeLabel, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
						.addComponent(TicketManagerLabel, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
						.addComponent(TicketPriceLabel, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
						.addComponent(TicketVenueLabel, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
						.addComponent(TicketEventLabel, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(593, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(263)
					.addComponent(PurchaseTicketsLabel, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(265, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(306)
					.addComponent(CancelButton, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
					.addGap(313))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(296)
					.addComponent(PurchaseButton, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(300, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addComponent(PurchaseTicketsLabel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addGap(33)
					.addComponent(TicketEventLabel, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(TicketVenueLabel, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(TicketManagerLabel, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(TicketPriceLabel, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(SelectAgeLabel, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(NumTicketsLabel, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(TotalChargeLabel, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
					.addComponent(PurchaseButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addGap(52)
					.addComponent(CancelButton)
					.addGap(67))
		);
		setLayout(groupLayout);

    }

}