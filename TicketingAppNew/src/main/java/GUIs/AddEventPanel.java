package GUIs;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import data.DataStore;
import data.dto.VenueDto;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.awt.event.ActionEvent;

public class AddEventPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField EventName;
	
	private JLabel titleLabel;
	private JLabel venueChoiceLabel;
	private JLabel EventNameLabel;
	private JLabel NumTicketsLabel;
	private JLabel DateChoiceLabel;
	
	private JComboBox<String> venueChoiceBox;
	
	private JSpinner numTicketsSpinner;
	
	private JButton AddButton;
	private JButton CancelButton;
	private static Date eventDate;
	
	/**
	 * Create the panel.
	 */
	public AddEventPanel() {
		
		titleLabel = new JLabel("Add an Event");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		venueChoiceLabel = new JLabel("Choose your venue");
		venueChoiceLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		venueChoiceBox = new JComboBox<String>();
		for (VenueDto venue : DataStore.getVenues()) {
			venueChoiceBox.addItem(venue.getLocation());
		}
		venueChoiceBox.addActionListener(new ActionListener() {
            @SuppressWarnings("unchecked")
			@Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<String> sourceComboBox = (JComboBox<String>) e.getSource();
                String selectedItem = (String) sourceComboBox.getSelectedItem();

                if (selectedItem != null) {
                    System.out.println("Selected Event " + selectedItem);
                }
            }
        });
		
		
		EventNameLabel = new JLabel("Name your Event");
		EventNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		EventName = new JTextField();
		EventName.setColumns(10);
		
		NumTicketsLabel = new JLabel("Choose number of tickets");
		NumTicketsLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		numTicketsSpinner = new JSpinner();
		numTicketsSpinner.setModel(new SpinnerNumberModel(0, 0, 500, 1));
		numTicketsSpinner.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JFormattedTextField textField = ((JSpinner.DefaultEditor) numTicketsSpinner.getEditor()).getTextField();
		textField.setFocusable(true);
		
		DateChoiceLabel = new JLabel("Choose Date");
		DateChoiceLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		AddButton = new JButton("Add Event");
		AddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		AddButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		CancelButton = new JButton("Cancel");
		CancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.swap("managerInfoPanel");
			}
		});
		CancelButton.setFont(new Font("Tahoma", Font.PLAIN, 15));

        JSpinner dateChooser = new JSpinner(new SpinnerDateModel(new Date(), null, null, java.util.Calendar.MINUTE));
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateChooser, "yyyy-MM-dd HH:mm");
        eventDate = (Date) dateChooser.getValue();
        dateChooser.setEditor(dateEditor);
        dateChooser.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                eventDate = (Date) dateChooser.getValue();
                System.out.println(eventDate.toString());
            }
        });
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(70)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(EventNameLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(venueChoiceLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
						.addComponent(NumTicketsLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(DateChoiceLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(numTicketsSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(EventName, GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
								.addComponent(venueChoiceBox, 0, 490, Short.MAX_VALUE))
							.addGap(235))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(459, Short.MAX_VALUE)
					.addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
					.addGap(263))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(279)
					.addComponent(AddButton)
					.addGap(50)
					.addComponent(CancelButton, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(429, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addComponent(titleLabel)
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(venueChoiceLabel)
						.addComponent(venueChoiceBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(EventNameLabel)
						.addComponent(EventName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(NumTicketsLabel)
						.addComponent(numTicketsSpinner, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(DateChoiceLabel)
						.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(AddButton)
						.addComponent(CancelButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(311, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
