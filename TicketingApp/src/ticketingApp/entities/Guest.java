package ticketingApp.entities;

import ticketingApp.RegisteredUser;

public class Guest extends User {
	public Guest() {}
	
	public RegisteredUser register(String name, String phone, String email, String password, boolean isManager) {
		return (new RegisteredUser(name, phone, email, password, isManager));
	}
}
