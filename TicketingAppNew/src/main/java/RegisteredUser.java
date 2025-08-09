import java.util.ArrayList;
import java.util.List;
import entities.Ticket;
import entities.User;

public class RegisteredUser extends User {
	private static final List<RegisteredUser> REGISTERED_USERS = new ArrayList<>();	private String name;
	private String phone;
	private String email;
	private String password;
	private boolean isManager;
	private static RegisteredUser currentUser;  
	private ArrayList<Ticket> tickets;
	
	// Constructor for registering a user
	public RegisteredUser(String name, String phone, String email, String password, boolean isManager) {
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.isManager = isManager;
		REGISTERED_USERS.add(this);
	}
	
	// Constructor for importing csv file
	public RegisteredUser(String[] csvFormat) {
		this.name = csvFormat[0];
		this.phone = csvFormat[1];
		this.email = csvFormat[2];
		this.password = csvFormat[3];
		this.isManager = Boolean.parseBoolean(csvFormat[6]);
		REGISTERED_USERS.add(this);
	}
	
	// GETTERS AND SETTERS
	public String getName() {return name;}
	public String getPhone() {return phone;}
	public String getEmail() {return email;}
	public boolean isManager() {return isManager;}
	public static RegisteredUser getCurrentUser() {return currentUser;}
	public ArrayList<Ticket> getCurrentTickets() {return tickets;}
	public List<RegisteredUser> getRegisteredUsers() {return REGISTERED_USERS;}
	
	/**
	 * This method checks the passed in password entry matches the password
	 * @param entry
	 * @return
	 */
	public boolean checkPassword(String entry) {return entry.equals(password);}


	/**
	 * This method prints user information
	 */
	public void print() {
		System.out.println("Name: " + this.name + "\nPhone: " + this.phone + "\nEmail: " + this.email);
	}
}
