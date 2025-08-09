package data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventDto {
    private String eventName;
    private ManagerDto manager;
    private int numTickets;
    private int numTicketsRemaining;
    private String venue;
    private Date date;

    public EventDto() {}

    public EventDto(String eventName, ManagerDto manager, int numTickets, int numTicketsRemaining, String venue, Date date) {
        this.eventName = eventName;
        this.manager = manager;
        this.numTickets = numTickets;
        this.numTicketsRemaining = numTicketsRemaining;
        this.venue = venue;
        this.date = date;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public ManagerDto getManager() {
        return manager;
    }

    public void setManager(ManagerDto manager) {
        this.manager = manager;
    }

    public int getNumTickets() {
        return numTickets;
    }

    public void setNumTickets(int numTickets) {
        this.numTickets = numTickets;
    }

    public int getNumTicketsRemaining() {
        return numTicketsRemaining;
    }

    public void setNumTicketsRemaining(int numTicketsRemaining) {
        this.numTicketsRemaining = numTicketsRemaining;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
