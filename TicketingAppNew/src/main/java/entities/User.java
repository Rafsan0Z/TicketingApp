package entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class User {
	private List<Ticket> tickets = new ArrayList<>();
  
	public User(String[] userInfo) {} // For signed in users
	
	public User() {} // For unsigned in users
	
	public Event[] viewAllEvents() {
		// TODO implement this function when Event class is built
		return new Event[2];
	}
	
	
	// TODO this might not be the right place for this method
	public List<Ticket> getCurrentTickets() {
		List<Ticket> current = new ArrayList<Ticket>();
		for (Ticket ticket : tickets) {
			if (ticket.getEvent().getDate().after(new Date())) {
				current.add(ticket);
			}
		}
		return current;
	}
	
	
}
