package data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class VenueDto {

    @JsonProperty("name")
    private String name;
    @JsonProperty("location")
    private String location;
    @JsonProperty("capacity")
    private int capacity;
    @JsonProperty("scheduledEvents")
    private List<EventDto> scheduledEvents = new ArrayList<EventDto>();

    public VenueDto() {}

    public VenueDto(String name, String location, int capacity, List<EventDto> scheduledEvents) {
        this.location = location;
        this.capacity = capacity;
        this.scheduledEvents = scheduledEvents;
    }

    public void setCapacity(int cap) {capacity = cap;}
    public String getLocation() {return location;}
    public int getCapacity() {return capacity;}

    public List<EventDto> getScheduledEvents() {return scheduledEvents;}
    public EventDto[] getAvailableEvents() {
    	EventDto[] events = new EventDto[scheduledEvents.size()];
    	int i = 0;
        for (EventDto event : scheduledEvents) {
            if (!event.isSoldOut()) {
            	events[i] = event;
            	i++;
            }
        }
        return events;
    }

    public void scheduleEvent(EventDto event) {
        scheduledEvents.add(event);
    }
    public void cancelEvent(EventDto event) {
        scheduledEvents.remove(event);
    }
    public boolean checkDateAvailability(Date toCheck) {
        for (EventDto event : scheduledEvents) {
            if (event.getDate().equals(toCheck)) {
                return false;
            }
        }
        return true;
    }
}