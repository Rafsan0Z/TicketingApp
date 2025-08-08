package ticketingApp.entities;

import java.util.Date;

public class Ticket {

	private Event event;
	private Venue venue;
	private Date time;
	private double cost;
	
	public Ticket(Event event, double cost) {
		this.event = event;
		this.venue = event.getVenue();
		this.time = event.getDate();
		this.cost = cost;
	}
	
	public Ticket(String[] ticketInfo) {
		// TODO Auto-generated constructor stub
	}

	public Event getEvent() {return event;	}
	public Venue getVenue() {return venue;}
	public Date getTime() {return time;}
	public double getCost() {return cost;}

}
