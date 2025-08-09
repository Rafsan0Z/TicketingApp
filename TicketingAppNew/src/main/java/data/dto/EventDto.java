package data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventDto {
    private String EventName;
    private ManagerDto Manager;
    private int numTickets;
    private int numTicketsRemaining;
    private String venue;
    private Date date;

    public EventDto() {}

    public EventDto(String eventName, ManagerDto manager, int numTickets, int numTicketsRemaining, String venue, Date date) {
        EventName = eventName;
        Manager = manager;
        this.numTickets = numTickets;
        this.numTicketsRemaining = numTicketsRemaining;
        this.venue = venue;
        this.date = date;
    }

    public String getEventName() {
        return EventName;
    }

    public void setEventName(String eventName) {
        EventName = eventName;
    }

    public ManagerDto getManager() {
        return Manager;
    }

    public void setManager(ManagerDto manager) {
        Manager = manager;
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
