package GUIs;

import data.DataStore;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class PurchaseConfirmation extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField childField;
	private JTextField adultField;
	private JTextField seniorField;

	/**
	 * Create the panel.
	 */
	public PurchaseConfirmation(String eventName) {
		getContentPane().setLayout(null);
		setBounds(50, 50, 310, 290);
		setLocationRelativeTo(null);
		
		JLabel childLabel = new JLabel("Child:");
		childLabel.setBounds(49, 82, 61, 16);
		getContentPane().add(childLabel);
		
		JLabel adultLabel = new JLabel("Adult:");
		adultLabel.setBounds(49, 124, 61, 16);
		getContentPane().add(adultLabel);
		
		JLabel seniorLabel = new JLabel("Senior:");
		seniorLabel.setBounds(49, 169, 61, 16);
		getContentPane().add(seniorLabel);
		
		JLabel titleLabel = new JLabel("Purchase Ticket");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(79, 16, 153, 16);
		getContentPane().add(titleLabel);
		
		JLabel descriptionLabel = new JLabel("Input amount for each");
		descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		descriptionLabel.setBounds(79, 44, 153, 16);
		getContentPane().add(descriptionLabel);
		
		JButton confirmBtn = new JButton("Purchase");
        confirmBtn.addActionListener((ActionEvent e) -> {
            try {
                int numChild = childField.getText().isEmpty() ? 0 : Integer.parseInt(childField.getText());
                int numAdult = adultField.getText().isEmpty() ? 0 : Integer.parseInt(adultField.getText());
                int numSenior = seniorField.getText().isEmpty() ? 0 : Integer.parseInt(seniorField.getText());
                for (int i = 0; i < numChild; i++) {
                    DataStore.buyTicket(eventName, "Child");
                }
                for (int i = 0; i < numAdult; i++) {
                    DataStore.buyTicket(eventName, "Adult");
                }
                for (int i = 0; i < numSenior; i++) {
                    DataStore.buyTicket(eventName, "Senior");
                }
                dispose();
                AccountInfoPanel.loadTables();
                EventViewerPanel.refreshEvents();
                MainFrame.swap("userinfo");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid integers.");
            }
        });
		confirmBtn.setBounds(169, 206, 117, 29);
		getContentPane().add(confirmBtn);
		
		JButton cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener((ActionEvent e) -> {
            dispose();
        });
		cancelBtn.setBounds(26, 206, 117, 29);
		getContentPane().add(cancelBtn);
		
		childField = new JTextField();
		childField.setBounds(117, 80, 130, 26);
		getContentPane().add(childField);
		childField.setColumns(10);
		
		adultField = new JTextField();
		adultField.setColumns(10);
		adultField.setBounds(117, 122, 130, 26);
		getContentPane().add(adultField);
		
		seniorField = new JTextField();
		seniorField.setColumns(10);
		seniorField.setBounds(117, 164, 130, 26);
		getContentPane().add(seniorField);

	}
}
