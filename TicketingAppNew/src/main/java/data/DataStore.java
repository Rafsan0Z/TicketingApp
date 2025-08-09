package data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import data.dto.EventDto;
import data.dto.ManagerDto;
import data.dto.TicketDto;
import data.dto.UserDto;
import data.dto.VenueDto;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
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

            EVENTS.addAll(MAPPER.readValue(managerJSON, new TypeReference<List<EventDto>>() {
            }));
            
            VENUES.addAll(MAPPER.readValue(jsonString, new TypeReference<List<VenueDto>>() {}));
            TICKETS.addAll(MAPPER.readValue(jsonString, new TypeReference<List<TicketDto>>() {}));


            System.out.println("Reading events...done amount=" + EVENTS.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void SaveUsers() {
        try {
            String jsonString = MAPPER.writeValueAsString(USERS);
            File userOutFile = new File("src/users.json");
            MAPPER.writeValue(userOutFile, jsonString);

            String managerJSON = MAPPER.writeValueAsString(USERS);
            File managerOutFile = new File("src/managers.json");
            MAPPER.writeValue(managerOutFile, managerJSON);

            String eventJSON = MAPPER.writeValueAsString(USERS);
            File eventOutFile = new File("src/events.json");
            MAPPER.writeValue(eventOutFile, eventJSON);
            

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
