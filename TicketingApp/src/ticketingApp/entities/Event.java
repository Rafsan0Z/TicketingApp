package ticketingApp.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ticketingApp.RegisteredUser;

public class Event {
	private String name;
	private Venue venue;
	private int maxTickets;
	private Date date;
	private List<Ticket> tickets = new ArrayList<Ticket>();
	private boolean soldOut;
	
	public Event(String name, Venue venue, int maxTickets, Date date) {
		this.name = name;
		this.venue = venue;
		this.maxTickets = maxTickets;
		this.date = date;
	}

	public Event(String[] eventInfo) {
		// TODO Auto-generated constructor stub
	}

	public String getName() {return name;}
	public Venue getVenue() {return venue;}
	public int getMaxTickets() {return maxTickets;}
	public Date getDate() {return date;}
	public List<Ticket> getAllTickets() {return tickets;}
	public List<Ticket> getAvailableTickets() {
		List<Ticket> availableTickets = new ArrayList<Ticket>();
		for (Ticket ticket : tickets) {
			if (!ticket.isSold()) {
				availableTickets.add(ticket);
			}
		}
		return availableTickets;
	}
	public List<Ticket> getPurchasedTickets() {
		List<Ticket> purchasedTickets = new ArrayList<Ticket>();
		for (Ticket ticket : tickets) {
			if (ticket.isSold()) {
				purchasedTickets.add(ticket);
			}
		}
		return purchasedTickets;
	}
	
	public boolean isSoldOut() {
		if (getPurchasedTickets().isEmpty()) {
			return true;
		}
		return false;
	}
	
	public void cancelAllTickets() {
		for (Ticket ticket : tickets) {
			if (ticket.isSold()) {
				RegisteredUser user = ticket.getOwner();
				user.getCurrentTickets().remove(ticket);
			}
		}
		
		tickets.removeAll(tickets);
	}

}
