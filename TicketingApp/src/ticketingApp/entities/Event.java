package ticketingApp.entities;

import java.util.ArrayList;
import java.util.Date;

public class Event {
	private String name;
	private Venue venue;
	private int capacity;
	private Date date;
	private ArrayList<Ticket> tickets;
	
	public Event(String name, Venue venue, int capacity, Date date) {
		this.name = name;
		this.venue = venue;
		this.capacity = capacity;
		this.date = date;
	}

	public Event(String[] eventInfo) {
		// TODO Auto-generated constructor stub
	}

	public String getName() {return name;}
	public Venue getVenue() {return venue;}
	public int getCapacity() {return capacity;}
	public Date getDate() {return date;}
	public ArrayList<Ticket> getTickets() {return tickets;}

}
