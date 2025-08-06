package ticketingApp;

public class Ticket {

	private String event;
	private String venue;
	private String time;
	private double cost;
	
	public Ticket(String event, String venue, String time, double cost) {
		this.event = event;
		this.venue = venue;
		this.time = time;
		this.cost = cost;
	}
	
	public Ticket(String[] ticketInfo) {
		// TODO Auto-generated constructor stub
	}

	public String getEvent() {
		return event;
	}

	public String getVenue() {
		return venue;
	}

	public String getTime() {
		return time;
	}

	public double getCost() {
		return cost;
	}

}
