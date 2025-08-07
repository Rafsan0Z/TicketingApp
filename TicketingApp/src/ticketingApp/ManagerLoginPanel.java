package ticketingApp;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class ManagerLoginPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField emailField;
	private JPasswordField managerPin;
	private JTextField phoneField;

	/**
	 * Create the panel.
	 */
	public ManagerLoginPanel() {
setLayout(null);
        
        JLabel titleLabel = new JLabel("Manager Login");
        titleLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(234, 34, 332, 58);
        add(titleLabel);
        
        emailField = new JTextField();
        emailField.setBounds(317, 197, 249, 26);
        add(emailField);
        emailField.setColumns(10);
        
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        emailLabel.setBounds(234, 201, 61, 16);
        add(emailLabel);
        
        JLabel phoneLable = new JLabel("Phone:");
        phoneLable.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        phoneLable.setBounds(234, 254, 61, 16);
        add(phoneLable);
        
        phoneField = new JTextField();
        phoneField.setColumns(10);
        phoneField.setBounds(317, 250, 249, 26);
        add(phoneField);
        
        JLabel managerPinLabel = new JLabel("Manager Pin:");
        managerPinLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        managerPinLabel.setBounds(234, 305, 106, 16);
        add(managerPinLabel);
        
        managerPin = new JPasswordField();
        managerPin.setBounds(352, 301, 214, 26);
        add(managerPin);
        
        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(449, 352, 117, 29);
        add(loginBtn);
        
	}

}
