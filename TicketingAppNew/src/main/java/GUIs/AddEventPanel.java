package GUIs;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddEventPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField EventName;
	private JTextField DateChoice;
	
	private JLabel TitleLabel;
	private JLabel VenueChoiceLabel;
	private JLabel EventNameLabel;
	private JLabel NumTicketsLabel;
	private JLabel DateChoiceLabel;
	
	private JComboBox VenueChoiceBox;
	
	private JSpinner NumTicketsSpinner;
	
	private JButton AddButton;
	private JButton CancelButton;
	
	/**
	 * Create the panel.
	 */
	public AddEventPanel() {
		
		TitleLabel = new JLabel("Add an Event");
		TitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		TitleLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		VenueChoiceLabel = new JLabel("Choose your venue");
		VenueChoiceLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		VenueChoiceBox = new JComboBox();
		
		EventNameLabel = new JLabel("Name your Event");
		EventNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		EventName = new JTextField();
		EventName.setColumns(10);
		
		NumTicketsLabel = new JLabel("Choose number of tickets");
		NumTicketsLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		NumTicketsSpinner = new JSpinner();
		NumTicketsSpinner.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		NumTicketsSpinner.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		DateChoiceLabel = new JLabel("Choose Date");
		DateChoiceLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		DateChoice = new JTextField();
		DateChoice.setColumns(10);
		
		AddButton = new JButton("Add Event");
		AddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		AddButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		CancelButton = new JButton("Cancel");
		CancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		CancelButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(70)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(EventNameLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(VenueChoiceLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
						.addComponent(NumTicketsLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(DateChoiceLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(DateChoice, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
						.addComponent(NumTicketsSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(EventName, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
						.addComponent(VenueChoiceBox, 0, 233, Short.MAX_VALUE))
					.addGap(235))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(290, Short.MAX_VALUE)
					.addComponent(TitleLabel, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
					.addGap(263))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(270)
					.addComponent(AddButton)
					.addGap(50)
					.addComponent(CancelButton, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(278, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addComponent(TitleLabel)
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(VenueChoiceLabel)
						.addComponent(VenueChoiceBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(EventNameLabel)
						.addComponent(EventName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(NumTicketsLabel)
						.addComponent(NumTicketsSpinner, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(DateChoiceLabel)
						.addComponent(DateChoice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(AddButton)
						.addComponent(CancelButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(322, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}