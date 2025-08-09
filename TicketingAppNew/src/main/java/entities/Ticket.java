package entities;

public class Ticket {

	private Event event;
	private double price;
	private boolean purchased;
	private double discount;
	private User owner;
	
	public Ticket(Event event, double cost) {
		this.event = event;
		this.price = cost;
		this.owner = null;
		purchased = false;
		
	}
	
	public Ticket(String[] ticketInfo) {
		// TODO Auto-generated constructor stub
	}
	

	public Event getEvent() {return event;	}
	public double getPrice() {return price;}
	public User getOwner() {return owner;}
	public boolean isPurchased() {return purchased;}
	
	public void changePrice(double price) {this.price = price;}
	public void purchase(User owner) {
		this.owner = owner;
		purchased = true;
	}
	
	public void sell() {
		owner = null;
		purchased = false;
	}
	
}
