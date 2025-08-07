package ticketingApp.GUIs;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LoginPanel extends JPanel {
    
    private static final long serialVersionUID = 1L;
	private JTextField emailField;
	private JPasswordField passwordField;

	public LoginPanel(){
		setLayout(null);
        
        JLabel titleLabel = new JLabel("Welcome to Ticketing Application");
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
        
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        passwordLabel.setBounds(234, 251, 80, 16);
        add(passwordLabel);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(317, 247, 249, 26);
        add(passwordField);
        
        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(91, 332, 142, 63);
        add(loginBtn);
        
        JButton registerBtn = new JButton("Register");
        registerBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        registerBtn.setBounds(245, 332, 142, 63);
        add(registerBtn);
        
        JButton managerBtn = new JButton("Login as Manager");
        managerBtn.setBounds(567, 332, 142, 63);
        add(managerBtn);
        
        JButton guestBtn = new JButton("Login as Guest");
        guestBtn.setBounds(413, 332, 142, 63);
        add(guestBtn);

    }

}
