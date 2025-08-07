package ticketingApp;

import java.util.ArrayList;
import java.util.List;

public class RegisteredUser extends User {
	private static final List<RegisteredUser> REGISTERED_USERS = new ArrayList<>();
	private String name;
	private String phone;
	private String email;
	private String password;
	private ArrayList<Ticket> tickets;
	
	// Constructor for registering a user
	public RegisteredUser(String name, String phone, String email, String password) {
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.password = password;
		REGISTERED_USERS.add(this);
	}
	
	// Constructor for importing csv file
	public RegisteredUser(String[] csvFormat) {
		this.name = csvFormat[0];
		this.phone = csvFormat[1];
		this.email = csvFormat[2];
		this.password = csvFormat[3];
		REGISTERED_USERS.add(this);
	}
	
	// GETTERS AND SETTERS
	public String getName() {return name;}
	public String getPhone() {return phone;}
	public String getEmail() {return email;}
	public ArrayList<Ticket> getCurrentTickets() {return tickets;}
	public List<RegisteredUser> getRegisteredUsers() {return REGISTERED_USERS;}
	
	/**
	 * This method checks the passed in password entry matches the password
	 * @param entry
	 * @return
	 */
	public boolean checkPassword(String entry) {return entry.equals(password);}

	public static boolean login(String email, String phone, String password) {
		var found = REGISTERED_USERS
			.stream()
			.filter(u -> u.checkPassword(password) && u.getEmail().equals(email) && u.getPhone().equals(phone)).findFirst().orElse(null);
  
		return found != null;
	}
	
	/**
	 * This method prints user information
	 */
	public void print() {
		System.out.println("Name: " + this.name + "\nPhone: " + this.phone + "\nEmail: " + this.email);
	}
}
