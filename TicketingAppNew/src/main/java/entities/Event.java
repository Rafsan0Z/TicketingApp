package entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Event {
	private String name;
	private Venue venue;
	private int maxTickets;
	private Date date;
	private List<Ticket> tickets = new ArrayList<Ticket>();
	
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
			if (!ticket.isPurchased()) {
				availableTickets.add(ticket);
			}
		}
		return availableTickets;
	}
	public List<Ticket> getPurchasedTickets() {
		List<Ticket> purchasedTickets = new ArrayList<Ticket>();
		for (Ticket ticket : tickets) {
			if (ticket.isPurchased()) {
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
	
	public void changeTicketPrice(double newPrice) {
		for (Ticket ticket : tickets) {
			if (!ticket.isPurchased()) {
				ticket.changePrice(newPrice);
			}
		}
	}
	
	public void cancelAllTickets() {
		for (Ticket ticket : tickets) {
			if (ticket.isPurchased()) {
				User user = ticket.getOwner();
				user.getCurrentTickets().remove(ticket);
			}
		}
		
		tickets.removeAll(tickets);
	}

}