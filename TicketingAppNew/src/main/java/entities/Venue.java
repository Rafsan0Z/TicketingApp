package entities;

import java.util.ArrayList;

public class Venue {
	private String name;
	private int capacity;
	private ArrayList<Event> scheduledEvents;
	
	public Venue(String name, int capacity) {
		this.name = name;
		this.capacity = capacity;
	}
	public Venue(String[] venueInfo) {
		// TODO Auto-generated constructor stub
	}
	public String getName() {return name;}
	public int getCapacity() {return capacity;}
	public ArrayList<Event> getScheduledEvents() {return scheduledEvents;}

}
