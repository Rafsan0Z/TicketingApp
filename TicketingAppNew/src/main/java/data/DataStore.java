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

        EventDto newEvent = new EventDto(eventName, numTickets, numTicketsRemaining, cost, venue, date);
        currentVenue.scheduleEvent(newEvent);
        EVENTS.add(newEvent);
        saveEverything();
        return true;
    }
    
    // will try to buy ticket, return true if successful, false if not
    public static void buyTicket(String eventName, String ageType) {
        EventDto currentEvent = null;
        for (EventDto event : EVENTS) {
            if (eventName.equals(event.getEventName())) {
                currentEvent = event;
            }
        }
        if (currentEvent != null && !currentEvent.isSoldOut()) {
            double cost = Math.round(currentEvent.getCost() * discountAmount(ageType) * 100.0) / 100.0;
            TicketDto newTicket = new TicketDto(eventName, cost, ageType, true, currentUser.getEmail());

            TicketInfo newTicketInfo = new TicketInfo(currentUser.getEmail(), newTicket.getTicketId()); // for the event.getAttendees()
            currentEvent.setNumTicketsRemaining(currentEvent.getNumTicketsRemaining() - 1); // decrement the remaining tickets

            currentEvent.getAttendees().add(newTicketInfo);
            currentUser.getTickets().add(newTicket);
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

    public static TicketDto[] getTickets() {
    	TicketDto[] tickets = new TicketDto[TICKETS.size()];
    	int i = 0;
    	for (TicketDto ticket : TICKETS) {
    		tickets[i] = ticket;
    	}
    	return tickets;
//    	return (TicketDto[]) (TICKETS.toArray());
    }
    public static void setCurrentUser() { currentUser = null;}
    public static void logoutCurrentManager() {currentManager = null;}
    
    public static EventDto[] getEvents() {
    	EventDto[] events = new EventDto[EVENTS.size()];
    	int i = 0;
    	for (EventDto event : EVENTS) {
    		events[i] = event;
    	}
    	return events;
    }
    
    public static VenueDto[] getVenues() {
    	VenueDto[] venues = new VenueDto[VENUES.size()];
    	int i = 0;
    	for (VenueDto venue : VENUES) {
    		venues[i] = venue;
    	}
    	return venues;
    }

    public static EventDto[] getAvailableEvents() {
        return EVENTS.stream()
                .filter(e -> !e.isSoldOut())
                .toArray(EventDto[]::new);
    }
}
