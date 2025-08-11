package data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventDto {

    @JsonProperty("eventName")
    private String eventName;
    @JsonProperty("numTickets")
    private int numTickets;
    @JsonProperty("numTicketsRemaining")
    private int numTicketsRemaining;
    @JsonProperty("cost")
    private double cost;
    @JsonProperty("venue")
    private String venue;
    @JsonProperty("date")
    private Date date;
    @JsonProperty("attendees")
    private List<TicketInfo> attendees;

    public EventDto() {}

    public EventDto(String eventName, int numTickets, int numTicketsRemaining, double cost, String venue, Date date) {
        this.eventName = eventName;
        this.numTickets = numTickets;
        this.numTicketsRemaining = numTicketsRemaining;
        this.cost = cost;
        this.venue = venue;
        this.date = date;
        this.attendees = new ArrayList<TicketInfo>();
    }

    // Setters
    public void setEventName(String eventName) {this.eventName = eventName;}
    public void setDate(Date date) {this.date = date;}
    public void setVenue(String venue) {this.venue = venue;}
    public void setCost(double cost) {this.cost = cost;}
    public void setNumTicketsRemaining(int numTicketsRemaining) {this.numTicketsRemaining = numTicketsRemaining;}
    public void setNumTickets(int numTickets) {this.numTickets = numTickets;}

    // Getters
    public String getEventName() {return eventName;}
    public String getVenue() {return venue;}
    public Date getDate() {return date;}
    public int getNumTickets() {return numTickets;}
    public int getNumTicketsRemaining() {return numTicketsRemaining;}
    public double getCost() {return cost;}

    public List<TicketInfo> getAttendees() {
        return attendees;
    }

    public boolean isSoldOut() {
        if (numTicketsRemaining == 0) {
            return true;
        }
        return false;
    }

    public void changeTicketPrice(double newPrice) {cost = newPrice;}
    
    public boolean pastTicket() {
    	if (this.date.before(new Date())) {
    		return true;
    	}
    	return false;
    }

}