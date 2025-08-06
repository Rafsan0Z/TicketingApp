package ticketingApp;

import java.util.ArrayList;

public class RegisteredUser extends User {
	private static final ArrayList<User> REGISTERED_USERS = new ArrayList<User>();
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
	public ArrayList<User> getRegisteredUsers() {return REGISTERED_USERS;}
	
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
