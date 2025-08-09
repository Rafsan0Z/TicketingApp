package data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

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

    // Mapping for an array of Users used for canceling
    private UserDto[] users;

    public EventDto() {}

    public EventDto(String eventName, int numTickets, int numTicketsRemaining, String venue, Date date) {
        this.eventName = eventName;
        this.numTickets = numTickets;
        this.venue = venue;
        this.date = date;
        this.numTicketsRemaining = numTicketsRemaining;
    }

    // Setters
    public void setEventName(String eventName) {this.eventName = eventName;}
    public void setDate(Date date) {this.date = date;}
    public void setNumTicketsRemaining(int numTicketsRemaining) {this.numTicketsRemaining = numTicketsRemaining;}
    public void setNumTickets(int numTickets) {this.numTickets = numTickets;}

    // Getters
    public String getEventName() {return eventName;}
    public String getVenue() {return venue;}
    public Date getDate() {return date;}
    public int getNumTickets() {return numTickets;}
    public int getNumTicketsRemaining() {return numTicketsRemaining;}
    public double getCost() {return cost;}

    public boolean isSoldOut() {
        if (numTicketsRemaining == 0) {
            return true;
        }
        return false;
    }

    public void changeTicketPrice(double newPrice) {cost = newPrice;}

//	public void cancelAllTickets() {
//		for (UserDto user : users) {
//			user.getCurrentTickets();
//			// TODO: add remove
//		}
//		numTicketsRemaining = 0;
//	}

}