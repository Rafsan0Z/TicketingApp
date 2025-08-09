package GUIs;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import MainFrame;

public class EventViewerPanel extends JPanel{

    private static final long serialVersionUID = 1L;
    private JTable EventTable;
    
    private JLabel TopicLabel;
    
    private JButton PurchaseButton;
    private JButton AccountButton;

	public EventViewerPanel(){

		TopicLabel = new JLabel("View all Available Events");
		TopicLabel.setHorizontalAlignment(SwingConstants.CENTER);
		TopicLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		EventTable = new JTable();
		EventTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		PurchaseButton = new JButton("Purchase Tickets");
		PurchaseButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		AccountButton = new JButton("Account Info");
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
							.addComponent(EventTable, GroupLayout.PREFERRED_SIZE, 753, GroupLayout.PREFERRED_SIZE)))
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
					.addComponent(EventTable, GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(PurchaseButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(40))
		);
		setLayout(groupLayout);
    }

}