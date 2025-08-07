package ticketingApp;

import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ManagerLoginPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField emailField;
    private JPasswordField managerPasswordField;
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

        JLabel managerPasswordLabel = new JLabel("Manager Pin:");
        managerPasswordLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        managerPasswordLabel.setBounds(234, 305, 106, 16);
        add(managerPasswordLabel);

        managerPasswordField = new JPasswordField();
        managerPasswordField.setBounds(352, 301, 214, 26);
        add(managerPasswordField);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(449, 352, 117, 29);
        add(loginBtn);

        loginBtn.addActionListener((ActionEvent e) -> {
            if (emailField.getText().isEmpty() || phoneField.getText().isEmpty()
                    || managerPasswordField.getText().isEmpty()) {
                System.out.println("ERROR!");
                return;
            }

            if (RegisteredUser.login(emailField.getText(), phoneField.getText(), managerPasswordField.getText())){
                System.out.println("SUCCESS");
            } else {
                System.out.println("NO SUCCESS");
            }
        });
    }
}
