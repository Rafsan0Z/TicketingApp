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

    private static UserDto currentUser;
    private static ManagerDto currentManager;

    // this bool tracks if the current person signed in is a user or a manager
    private static boolean isCurrentUser;

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
            // saving the current person logged in
            if (isCurrentUser) {
                saveCurrentUser(currentUser);
            } else if (!isCurrentUser) {
                saveCurrentManager(currentManager);
            }

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
            isCurrentUser = true;
            currentUser = found;
        }

        return found != null;
    }
    public static boolean loginManager(String email, String password) {
        var found = MANAGERS
                .stream()
                .filter(u -> u.checkPassword(password) && u.getEmail().equals(email)).findFirst().orElse(null);

        if (found != null) {
            isCurrentUser = false;
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

    public static void saveCurrentUser(UserDto currentUser) {
        UserDto foundUser = null;
        for (UserDto user : USERS) {
            if(currentUser.getEmail().equals(user.getEmail())) {
                foundUser = user;
            }
        }
        if (foundUser != null) {
            USERS.remove(foundUser);
            USERS.add(currentUser);
        }
    }

    public static void saveCurrentManager(ManagerDto currentManager) {
        ManagerDto foundManager = null;
        for (ManagerDto manager : MANAGERS) {
            if(currentManager.getEmail().equals(manager.getEmail())) {
                foundManager = manager;
            }
        }
        if (foundManager != null) {
            MANAGERS.remove(foundManager);
            MANAGERS.add(currentManager);
        }
    }

    public static UserDto getCurrentUser() {
        return currentUser;
    }
    public static ManagerDto getCurrentManager() {
        return currentManager;
    }
    public static boolean isCurrentUser() {
        return isCurrentUser;
    }
}
