import data.DataStore;

import java.awt.Font;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterPanel extends JPanel{


    private static final long serialVersionUID = 1L;
	private JTextField nameField;
	private JPasswordField confirmField;
	private JTextField phoneField;
	private JTextField emailField;
	private JPasswordField passwordField;

	public RegisterPanel(){
		setLayout(null);

		JLabel titleLabel = new JLabel("Register");
        titleLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(361, 56, 78, 25);
        add(titleLabel);
        
        nameField = new JTextField();
        nameField.setBounds(315, 134, 334, 26);
        add(nameField);
        nameField.setColumns(10);
        
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        nameLabel.setBounds(169, 141, 46, 19);
        add(nameLabel);
        
        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        phoneLabel.setBounds(169, 216, 48, 19);
        add(phoneLabel);
        
        confirmField = new JPasswordField();
        confirmField.setBounds(317, 434, 332, 26);
        add(confirmField);
        
        JLabel emailLabel = new JLabel("Email: ");
        emailLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        emailLabel.setBounds(169, 291, 48, 19);
        add(emailLabel);
        
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        passwordLabel.setBounds(169, 366, 73, 19);
        add(passwordLabel);
        
        JLabel confirmLabel = new JLabel("Confirm Password:");
        confirmLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        confirmLabel.setBounds(169, 441, 136, 19);
        add(confirmLabel);
        
        phoneField = new JTextField();
        phoneField.setColumns(10);
        phoneField.setBounds(315, 209, 334, 26);
        add(phoneField);
        
        emailField = new JTextField();
        emailField.setColumns(10);
        emailField.setBounds(315, 284, 334, 26);
        add(emailField);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(317, 359, 332, 26);
        add(passwordField);

        JButton registerUserBtn = new JButton("Register as User");
        registerUserBtn.setBounds(532, 513, 117, 29);
        add(registerUserBtn);
        registerUserBtn.addActionListener((ActionEvent e) -> {
            if (emailField.getText().isEmpty() || passwordField.getText().isEmpty() || nameField.getText().isEmpty() || phoneField.getText().isEmpty() || confirmField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(RegisterPanel.this, "Empty Fields!");
                return;
            }
            if (!confirmField.getText().equals(passwordField.getText())) {
                JOptionPane.showMessageDialog(RegisterPanel.this, "Passwords do not match!");
                return;
            }
            if(DataStore.registerUser(nameField.getText(), phoneField.getText(), emailField.getText(), passwordField.getText()) == false){
                JOptionPane.showMessageDialog(RegisterPanel.this, "Email already exists");
                return;
            };

            MainFrame.swap("login");
        });

        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainFrame.swap("login");
            }
        });
        cancelBtn.setBounds(269, 513, 117, 29);
        add(cancelBtn);

        JButton registerManagerBtn = new JButton("Register as Manager");
        registerManagerBtn.setBounds(386, 513, 140, 29);
        add(registerManagerBtn);
        registerManagerBtn.addActionListener((ActionEvent e) -> {
            if (emailField.getText().isEmpty() || passwordField.getText().isEmpty() || nameField.getText().isEmpty() || phoneField.getText().isEmpty() || confirmField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(RegisterPanel.this, "Empty Fields!");
                return;
            }
            if (!confirmField.getText().equals(passwordField.getText())) {
                JOptionPane.showMessageDialog(RegisterPanel.this, "Passwords do not match!");
                return;
            }
            if(DataStore.registerManager(nameField.getText(), phoneField.getText(), emailField.getText(), passwordField.getText()) == false){
                JOptionPane.showMessageDialog(RegisterPanel.this, "Email already exists");
                return;
            };

            MainFrame.swap("login");
        });


    }
}