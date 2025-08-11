package GUIs;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import data.dto.EventDto;
import java.awt.Font;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditEventPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static JTextField nameField;
	private static Date eventDate;
	private static JTextField costField;
	private static JSpinner dateChooser;

	/**
	 * Create the panel.
	 */
	public EditEventPanel() {
		setLayout(null);
		
		JLabel titleLabel = new JLabel("Edit Event");
        titleLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(234, 76, 332, 58);
        add(titleLabel);
        
        // Event name information
        JLabel nameLabel = new JLabel("Event Name:");
        nameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        nameLabel.setBounds(169, 210, 97, 19);
        add(nameLabel);
        
        nameField = new JTextField();
        nameField.setOpaque(true);
        nameField.setBounds(333, 207, 294, 26);
        add(nameField);
        nameField.setColumns(10);
        
        // Date information
        JLabel dateLabel = new JLabel("Event Date:");
        dateLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        dateLabel.setBounds(169, 305, 97, 19);
        add(dateLabel);
        
        dateChooser = new JSpinner(new SpinnerDateModel(new Date(), null, null, java.util.Calendar.MINUTE));
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateChooser, "yyyy-MM-dd HH:mm");
        dateChooser.setBounds(333,  302,  165,  26);
        eventDate = (Date) dateChooser.getValue();
        dateChooser.setEditor(dateEditor);
        dateChooser.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                eventDate = (Date) dateChooser.getValue();
                System.out.println(eventDate.toString());
            }
        });
        add(dateChooser);
        
        // cost information
        JLabel costLabel = new JLabel("Event Cost:");
        costLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        costLabel.setBounds(169, 400, 97, 19);
        add(costLabel);
        
        costField = new JTextField();
        costField.setColumns(10);
        costField.setBounds(333, 397, 138, 26);
        add(costField);
        
        // Save event
        JButton saveBtn = new JButton("Save");
        saveBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ManagerInfoPanel.refreshTable();
        		MainFrame.swap("managerInfoPanel");
        		JOptionPane.showMessageDialog(null, "Event updated");
        	}
        });
        saveBtn.setBounds(615, 495, 117, 29);
        add(saveBtn);
        
        // Delete event
        JButton deleteBtn = new JButton("Delete");
        deleteBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ManagerInfoPanel.refreshTable();
        		MainFrame.swap("managerInfoPanel");
        		JOptionPane.showMessageDialog(null, "Event deleted");
        	}
        });
        deleteBtn.setBounds(486, 495, 117, 29);
        add(deleteBtn);
		
	}

	/**
	 * This method will load event information into panel
	 * @param event
	 */
	public static void loadEventInfo(EventDto event) {
		if (event != null) {
			nameField.setText(event.getEventName());
			dateChooser.setValue(event.getDate());
			costField.setText(Double.toString(event.getCost()));
		}
	}
}
