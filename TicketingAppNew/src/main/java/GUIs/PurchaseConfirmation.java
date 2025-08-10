package GUIs;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PurchaseConfirmation extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	/**
	 * Create the panel.
	 */
	public PurchaseConfirmation() {
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
		confirmBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		confirmBtn.setBounds(169, 206, 117, 29);
		getContentPane().add(confirmBtn);
		
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		cancelBtn.setBounds(26, 206, 117, 29);
		getContentPane().add(cancelBtn);
		
		textField = new JTextField();
		textField.setBounds(117, 80, 130, 26);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(117, 122, 130, 26);
		getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(117, 164, 130, 26);
		getContentPane().add(textField_2);

	}
}
