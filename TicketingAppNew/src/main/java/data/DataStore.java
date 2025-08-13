package data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import data.dto.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

public class DataStore {
    private static final ObjectMapper MAPPER = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private static final List<UserDto> USERS = new ArrayList<>();
    private static final List<ManagerDto> MANAGERS = new ArrayList<>();
    private static final List<EventDto> EVENTS = new ArrayList<>();
    private static final List<TicketDto> TICKETS = new ArrayList<>();
    private static final List<VenueDto> VENUES = new ArrayList<>();

    // announcement: if you modify the current user/manager, it will also modify the user/manager in the list above
    // BECAUSE, the currentUser and currentManager are just pointers to the actual object in the list
    // so when you do saveEverything(), it should still save
    // apply this logic to the other lists too
    private static UserDto currentUser;
    private static ManagerDto currentManager;

    public enum Role { NONE, USER, MANAGER }

    private static Role currentRole = Role.NONE;
    public static Role getCurrentRole() { return currentRole; }
    public static void setCurrentRole(Role role) { currentRole = role; }

    static {
        try {
            System.out.println("Reading users...");
            String jsonString = new String(Files.readAllBytes(Path.of("src/users.json")));
            USERS.addAll(MAPPER.readValue(jsonString, new TypeReference<List<UserDto>>() {
            }));
            System.out.println("Reading users...done amount=" + USERS.size());

            System.out.println("Reading managers...");
            String managerJSON = new String(Files.readAllBytes(Path.of("src/managers.json")));
            MANAGERS.addAll(MAPPER.readValue(managerJSON, new TypeReference<List<ManagerDto>>() {
            }));
            System.out.println("Reading managers...done amount=" + MANAGERS.size());

            System.out.println("Reading Events...");
            String eventsJSON = new String(Files.readAllBytes(Path.of("src/events.json")));
            EVENTS.addAll(MAPPER.readValue(eventsJSON, new TypeReference<List<EventDto>>() {
            }));
            System.out.println("Reading events...done amount=" + EVENTS.size());

            
            System.out.println("Reading Tickets...");
            String ticketsJSON = new String(Files.readAllBytes(Path.of("src/tickets.json")));
            TICKETS.addAll(MAPPER.readValue(ticketsJSON, new TypeReference<List<TicketDto>>() {
            }));
            System.out.println("Reading Tickets...done amount=" + TICKETS.size());

            System.out.println("Reading Venues...");
            String venuesJSON = new String(Files.readAllBytes(Path.of("src/venues.json")));
            VENUES.addAll(MAPPER.readValue(venuesJSON, new TypeReference<List<VenueDto>>() {
            }));
            System.out.println("Reading Venues...done amount=" + VENUES.size());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveEverything() {
        try {

            File userOutFile = new File("src/users.json");
            MAPPER.writerWithDefaultPrettyPrinter().writeValue(userOutFile, USERS);

            File managerOutFile = new File("src/managers.json");
            MAPPER.writerWithDefaultPrettyPrinter().writeValue(managerOutFile, MANAGERS);

            File eventOutFile = new File("src/events.json");
            MAPPER.writerWithDefaultPrettyPrinter().writeValue(eventOutFile, EVENTS);

            File ticketOutFile = new File("src/tickets.json");
            MAPPER.writerWithDefaultPrettyPrinter().writeValue(ticketOutFile, TICKETS);

            File venuesOutFile = new File("src/venues.json");
            MAPPER.writerWithDefaultPrettyPrinter().writeValue(venuesOutFile, VENUES);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean loginUser(String email, String password) {
        var found = USERS
                .stream()
                .filter(u -> u.checkPassword(password) && u.getEmail().equals(email)).findFirst().orElse(null);

        if (found != null) {
            setCurrentRole(Role.USER);
            currentUser = found;
        }

        return found != null;
    }
    public static boolean loginManager(String email, String password) {
        var found = MANAGERS
                .stream()
                .filter(u -> u.checkPassword(password) && u.getEmail().equals(email)).findFirst().orElse(null);

        if (found != null) {
            setCurrentRole(Role.MANAGER);
            currentManager = found;
        }

        return found != null;
    }

    public static boolean isEmailUnique(String email) {
        for (UserDto user : USERS) {
            if (user.getEmail().equals(email)) {
                return false;
            }
        }
        for (ManagerDto manager : MANAGERS) {
            if (manager.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }

    public static boolean registerUser(String name, String phone, String email, String password) {
        if(isEmailUnique(email)) {
            UserDto newUser = new UserDto(name, phone, email, password, Collections.EMPTY_LIST);
            USERS.add(newUser);
            saveEverything();
            return true;
        } else {
            return false;
        }
    }
    public static boolean registerManager(String name, String phone, String email, String password) {
        if(isEmailUnique(email)) {
            ManagerDto newManager = new ManagerDto(name, phone, email, password, Collections.EMPTY_LIST);
            MANAGERS.add(newManager);
            saveEverything();
            return true;
        } else {
            return false;
        }
    }

    public static UserDto getCurrentUser() {return currentUser;}
    public static ManagerDto getCurrentManager() {return currentManager;}
    public static void logoutCurrentUser() {
        saveEverything();
        currentUser = null;
        currentManager = null;
        setCurrentRole(Role.NONE);
    }


    // create an event, return true if successful, false if not
    public static boolean createEvent(String eventName, int numTickets, int numTicketsRemaining, double cost, String venue, Date date){
        VenueDto currentVenue = null;
        for (VenueDto v : VENUES) {
            if (v.getLocation().equals(venue)) { // sets the current venue to the one with the matching name
                currentVenue = v;
                break;
            }
        }
        if (currentVenue == null || !currentVenue.checkDateAvailability(date)) return false;

        if (numTickets > currentVenue.getCapacity()) {
        	numTickets = currentVenue.getCapacity();
        	numTicketsRemaining = currentVenue.getCapacity();
        	JOptionPane.showMessageDialog(null, 
        			"Warning: Tickets excedes venue capacity. Number of tickets available has been set to "
        			+ numTickets);
        }
        
        EventDto newEvent = new EventDto(eventName, numTickets, numTicketsRemaining, cost, venue, date);
        currentVenue.scheduleEvent(newEvent);
        EVENTS.add(newEvent);
        saveEverything();
        return true;
    }

    // will try to buy ticket, return true if successful, false if not
    public static void buyTicket(String eventName, String ageType) {
        EventDto currentEvent = null;
        VenueDto currentVenue = null;
        for (EventDto event : EVENTS) {
            if (eventName.equals(event.getEventName())) {
                currentEvent = event;
            }
        }
        if (currentEvent == null) {return;}
        for (VenueDto v : VENUES) {
            if (v.getLocation().equals(currentEvent.getVenue())) {
                currentVenue = v;
            }
        }
        if (currentVenue == null) {return;}
        EventDto tmp = null;
        for (EventDto event : currentVenue.getScheduledEvents()) {
            if (event.getEventName().equals(eventName)) {
                tmp = event;
            }
        }
        if (!currentEvent.isSoldOut()) {
            double cost = Math.round(currentEvent.getCost() * discountAmount(ageType) * 100.0) / 100.0;
            TicketDto newTicket = new TicketDto(eventName, cost, ageType, true, currentUser.getEmail());

            TicketInfo newTicketInfo = new TicketInfo(currentUser.getEmail(), newTicket.getTicketId()); // for the event.getAttendees()
            currentEvent.setNumTicketsRemaining(currentEvent.getNumTicketsRemaining() - 1); // decrement the remaining tickets

            currentEvent.getAttendees().add(newTicketInfo);
            tmp.getAttendees().add(newTicketInfo);
            currentUser.addTicket(newTicket);
            
            TICKETS.add(newTicket);

            saveEverything();
        }
        
    }

    public static float discountAmount(String ageType) {
        if (ageType.trim().equalsIgnoreCase("child")) {
            return 0.9F;
        } else if (ageType.trim().equalsIgnoreCase("senior")) {
            return 0.85F;
        } else {
            return 1;
        }
    }

    
    /**
     * Search event list by name
     * @param name
     * @return EventDto
     */
    public static EventDto findEventByName(String name) {
    	for (EventDto event : EVENTS) {
    		if (event.getEventName().equals(name)) {
    			return event;
    		}
    	}
    	return null;
    }
    
    /**
     * Search venue list by name
     * @param name
     * @return
     */
    public static VenueDto findVenueByName(String name) {
    	for (VenueDto venue : VENUES) {
    		if (venue.getLocation().equals(name)) {
    			return venue;
    		}
    	}
    	return null;
    }
    
    public static UserDto findUserByEmail(String email) {
    	for (UserDto user: USERS) {
    		if (user.getEmail().equals(email)) {
    			return user;
    		}
    	}
    	return null;
    }
    
    public static TicketDto findTicketById(long id) {
    	for (TicketDto ticket : TICKETS) {
    		if (ticket.getTicketId() == id) {
    			return ticket;
    		}
    	}
    	return null;
    }

    public static TicketDto[] getTickets() {return TICKETS.toArray(TicketDto[]::new);}
    public static void setCurrentUser() { currentUser = null;}

    public static EventDto[] getEvents() {return EVENTS.toArray(EventDto[]::new);}    
    public static VenueDto[] getVenues() {return VENUES.toArray(VenueDto[]::new);}

    public static EventDto[] getAvailableEvents() {
        return EVENTS.stream()
                .filter(e -> !e.isSoldOut())
                .toArray(EventDto[]::new);
	}

    public static void updateEvent(EventDto eventSelected, String eventName, Date date, double cost) {
    	String ogName = eventSelected.getEventName();
        
        // update the venue.json
        VenueDto venue = findVenueByName(eventSelected.getVenue());
        for (EventDto event : venue.getScheduledEvents()) {
        	if (ogName.equals(event.getEventName())) {
        		event.setEventName(eventName);
        		event.setDate(date);
        		event.setCost(cost);
        		break;
        	}
        }
        
        // update the tickets.json
        /**
         * iterate through event attendees, for each attendee, find that attendee in the users
         * for the user's tickets, find event name and then change that
         * for every ticket id in the tickets, update the info
         */
        List<TicketInfo> attendees = eventSelected.getAttendees();
        long id;
        for (TicketInfo ticket : attendees) {
            String email = ticket.getEmail();
            id = ticket.getTicketId();
            for (TicketDto t : TICKETS) {
                if (t.getTicketId() == id) {
                    t.setEventName(eventName);
                }
            }
            for (UserDto user : USERS) {
                if (user.getEmail().equals(email)) {
                    for (TicketDto t : user.getTickets()) {
                        if (t.getTicketId() == id) {
                            t.setEventName(eventName);
                        }
                    }
                }
            }
        }
        eventSelected.setEventName(eventName);
    	eventSelected.setDate(date);
    	eventSelected.setCost(cost);
        saveEverything();
    }

    public static boolean checkValidTransfer(String name, String email) {
        if (name == null || email == null) return false;
        name  = name.trim();
        email = email.trim().toLowerCase();
        if (currentUser != null &&
                email.equalsIgnoreCase(currentUser.getEmail())) return false;
        for (UserDto user : USERS) {
            if (user == null) continue;
            String uName  = user.getName();
            String uEmail = user.getEmail();
            if (uName == null || uEmail == null) continue;
            if (uEmail.trim().equalsIgnoreCase(email) &&
                    uName.trim().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }


    public static void transferTicket(String eventName, long ticketId, String destEmail) {
        // update in the event json
        EventDto event = findEventByName(eventName);
        if (event == null) return;
        for (TicketInfo attendee : event.getAttendees()) {
            if (attendee.getTicketId() == ticketId) {
                attendee.setEmail(destEmail);
                break;
            }
        }
        // update in the venue json
        for (VenueDto v : VENUES) {
            if (v.getLocation().equals(event.getVenue())) {
                for (EventDto t : v.getScheduledEvents()) {
                    for (TicketInfo attendee : t.getAttendees()) {
                        if (attendee.getTicketId() == ticketId) {
                            attendee.setEmail(destEmail);
                            break;
                        }
                    }
                }
            }
        }
        // update in users json and tickets json
        TicketDto curTicket = null;
        for (TicketDto t : TICKETS) {
            if (t.getTicketId() == ticketId) {
                curTicket = t;
            }
        }
        if (curTicket == null) return;
        curTicket.setOwner(destEmail);
        currentUser.getTickets().removeIf(t -> t.getTicketId() == ticketId);
        for (UserDto user : USERS) {
            if (user.getEmail().equals(destEmail)) {
                user.getTickets().add(curTicket);
            }
        }

        saveEverything();
    }
    
    public static void cancelEvent(EventDto event) {
    	// update the tickets.json
        /**
         * iterate through event attendees, for each attendee, find that attendee in the users
         * for the user's tickets, find event name and then change that
         * for every ticket id in the tickets, update the info
         */
        List<TicketInfo> attendees = event.getAttendees();
        System.out.println(attendees.size());
        long id;
        String email;
        for (TicketInfo ticket : attendees) {
            email = ticket.getEmail();
            id = ticket.getTicketId();
            
            UserDto user = findUserByEmail(email);
            	
            TicketDto toRemove = null;
            if (user != null) {
            	for (TicketDto t : user.getTickets()) {
            		if (t.getTicketId() == id) {
            			toRemove = t;
            			break;
            		}
            	}
            	
            	// Remove event from user
            	if (toRemove != null) {    	
            		user.removeTicket(toRemove);
            	}
            }
            
            toRemove = null;
            for (TicketDto t : TICKETS) {
            	if (t.getTicketId() == id) {
            		toRemove = t;
            		break;
            	}
            }
            // Remove event from TICKETS
            if (toRemove != null)
            	TICKETS.remove(toRemove);   
            
        }
        
        // Remove event from venue
        VenueDto venue = findVenueByName(event.getVenue());
        System.out.println("Venue: " + venue.getLocation());
        venue.cancelEvent(event);
        
        // Remove event from EVENTS
        EVENTS.remove(event);
        
        saveEverything();
    }
}
