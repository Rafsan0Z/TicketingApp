package entities;

import java.util.ArrayList;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Venue implements Serializable {
	private static final long serialVersionUID = 1L;
	private String location;
	private int capacity;
	private List<Event> scheduledEvents = new ArrayList<Event>();
	
	public Venue(String location, int capacity) {
		this.location = location;
		this.capacity = capacity;
	}
	
	public Venue(String[] venueInfo) {
		System.out.println("hello");
		location = venueInfo[0];
		capacity = Integer.parseInt(venueInfo[1]);
	}
	
	public void setCapacity(int cap) {capacity = cap;}
	public String getLocation() {return location;}
	public int getCapacity() {return capacity;}
	
	public List<Event> getScheduledEvents() {return scheduledEvents;}
	
	public void scheduleEvent(Event event) {
		scheduledEvents.add(event);
	}
	public void cancelEvent(Event event) {
		scheduledEvents.remove(event);
	}
	public boolean checkDateAvailability(Date toCheck) {
		for (Event event : scheduledEvents) {
			if (event.getDate().equals(toCheck)) {
				return false;
			}
		}
		return true;
	}
}