package data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import data.DataStore;

import java.util.ArrayList;
import java.util.List;

// THIS IS BASICALLY THE REGISTERED USER

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    @JsonProperty("name")
    private String name;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;
    @JsonProperty("tickets")
    private List<TicketDto> tickets;

    public UserDto() {
    }

    public UserDto(String name, String phone, String email, String password, List<TicketDto> tickets) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.tickets = tickets;
    }

    public String getName() {
        return name;
    }

    public boolean checkPassword(String entry) {
        return entry.equals(password);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<TicketDto> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketDto> tickets) {
        this.tickets = tickets;
    }
    
    public double calculateTotal() {
    	double total = 0;
    	for (TicketDto ticket : tickets) {
    		total += ticket.getPrice();
    	}
    	return total;
    }

    @JsonIgnore
    public TicketDto[] getPassedTickets() {
        if (tickets == null) return new TicketDto[0];
        List<TicketDto> out = new ArrayList<>();
        for (TicketDto t : tickets) {
        	if (t.findEvent() != null) {
	            if (t.findEvent().pastTicket()) {
	                out.add(t);
	            }
        	}
        }
        return out.toArray(new TicketDto[0]);
    }

    @JsonIgnore
    public TicketDto[] getUpcomingTickets() {
        if (tickets == null) return new TicketDto[0];
        List<TicketDto> out = new ArrayList<>();
        for (TicketDto t : tickets) {
            if (!t.findEvent().pastTicket()) {
                out.add(t);
            }
        }
        return out.toArray(new TicketDto[0]);
    }

    public void updateUser(String name, String phone, String email, String password) {
    	setName(name);
        setPhone(phone);
        setEmail(email);
        setPassword(password);
        System.out.println(password);
        DataStore.saveEverything();
    }
    
    public void addTicket(TicketDto ticket) {
    	tickets.add(ticket);
    }
    
    public void removeTicket(TicketDto ticket) {
    	tickets.remove(ticket);
    }
}
