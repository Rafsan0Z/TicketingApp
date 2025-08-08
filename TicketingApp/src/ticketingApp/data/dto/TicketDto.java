package ticketingApp.data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TicketDto {

    @JsonProperty("ticketId")
    private int ticketId;
    @JsonProperty("eventName")
    private String eventName;
    @JsonProperty("manager")
    private String manager;
    @JsonProperty("price")
    private double price;
    @JsonProperty("purchased")
    private boolean purchased;
    @JsonProperty("venue")
    private String venue;

    public TicketDto(int ticketId, String eventName, String manager, double price, boolean purchased, String venue) {
        this.ticketId = ticketId;
        this.eventName = eventName;
        this.manager = manager;
        this.price = price;
        this.purchased = purchased;
        this.venue = venue;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isPurchased() {
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }
}
