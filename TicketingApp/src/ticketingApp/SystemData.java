package ticketingApp;

import java.util.ArrayList;
import java.util.List;

import ticketingApp.entities.Event;
import ticketingApp.entities.Ticket;
import ticketingApp.entities.Venue;
import ticketingApp.shipping.Serializables;

public class SystemData {
	public static final ArrayList<Venue> ALL_VENUES = new ArrayList<Venue>();
	private static final ArrayList<Event> ALL_EVENTS = new ArrayList<Event>();
	private static final ArrayList<Ticket> ALL_TICKETS = new ArrayList<Ticket>();
	private static final ArrayList<RegisteredUser> ALL_USERS = new ArrayList<RegisteredUser>();
//	private static final List<Manager> ALL_MANAGERS = new ArrayList<Manager>();
	
	public static List<Venue> getAllVeneus() {return ALL_VENUES;}
	public static List<Event> getAllEvents() {return ALL_EVENTS;}
	public static List<Ticket> getAllTickets() {return ALL_TICKETS;}
	public static List<RegisteredUser> getAllUsers() {return ALL_USERS;}
//	public static List<Manager> getAllManagers() {return ALL_MANAGERS;}
	
	public static void addVenue(Venue newVenue) {ALL_VENUES.add(newVenue);}
	public static void addAllVenue(ArrayList<Venue> superList) {ALL_VENUES.addAll(superList);}
	public static void addEvent(Event newEvent) {ALL_EVENTS.add(newEvent);}
	public static void addTicket(Ticket newTicket) {ALL_TICKETS.add(newTicket);}
	public static void addUser(RegisteredUser newUser) {ALL_USERS.add(newUser);}
//	public static void addManager(Manager newManager) {ALL_MANAGERS.add(newManager);}
	
	public static void serializeVenues() {
		Shipping.serialization(ALL_VENUES, Serializables.VENUES);
	}
	public static void serializeEvents() {
		Shipping.serialization(ALL_EVENTS, Serializables.EVENTS);
	}
	public static void serializeTickets() {
		Shipping.serialization(ALL_TICKETS, Serializables.TICKETS);
	}
	public static void serializeUsers() {
		Shipping.serialization(ALL_USERS, Serializables.USERS);
	}	
//	public static void serializeManagers() {
//		Shipping.serialization(ALL_MANAGERS, Serializables.MANAGERS);
//	}
	
	public static void deserialize() {
		Shipping.deserialization(ALL_VENUES, Serializables.VENUES);
	}
	
}
