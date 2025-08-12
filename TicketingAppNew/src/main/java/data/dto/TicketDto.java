package data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import data.DataStore;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TicketDto {

    @JsonProperty("ticketId")
    private long ticketId;
    @JsonProperty("eventName")
    private String eventName;
    @JsonProperty("price")
    private double price;
    @JsonProperty("ageType")
    private String ageType;
    @JsonProperty("purchased")
    private boolean purchased;
    @JsonProperty("owner")
    private String owner;

    public TicketDto() {
    }

    public TicketDto(String eventName, double price, String ageType, boolean purchased, String owner) {
        this.ticketId = System.currentTimeMillis() * 1000 + (int)(Math.random() * 1000);
        this.eventName = eventName;
        this.price = price;
        this.ageType = ageType;
        this.purchased = purchased;
        this.owner = owner;
    }

    public long getTicketId() {
        return ticketId;
    }

    public void setTicketId(long ticketId) {
        this.ticketId = ticketId;
    }

    public String getEventName() {
        return eventName;
    }

    public double getPrice() {
        return price;
    }

    public String getAgeType() {
        return ageType;
    }

    public String getOwner() {
        return owner;
    }

    public boolean isPurchased() {
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }

    public void changePrice(double price) {
        this.price = price;
    }

    public void purchase(String owner) {
        this.owner = owner;
        purchased = true;
    }

    public void sell() {
        owner = null;
        purchased = false;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public EventDto findEvent() {
    	return DataStore.findEventByName(eventName);
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
}