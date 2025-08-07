package ticketingApp;

public class Guest extends User {
	public Guest() {}
	
	public RegisteredUser register(String name, String phone, String email, String password) {
		return (new RegisteredUser(name, phone, email, password));
	}
}
