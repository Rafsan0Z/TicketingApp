package data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class VenueDto {

    @JsonProperty("location")
    private String location;
    @JsonProperty("capacity")
    private int capacity;
    @JsonProperty("scheduledEvents")
    private List<EventDto> scheduledEvents = new ArrayList<EventDto>();

    public VenueDto() {}
    public VenueDto(String location, int capacity) {
        this.location = location;
        this.capacity = capacity;
    }

    public VenueDto(String location, int capacity, List<EventDto> scheduledEvents) {
        this.location = location;
        this.capacity = capacity;
        this.scheduledEvents = scheduledEvents;
    }

    public void setCapacity(int cap) {capacity = cap;}
    public String getLocation() {return location;}
    public int getCapacity() {return capacity;}

    public List<EventDto> getScheduledEvents() {return scheduledEvents;}

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