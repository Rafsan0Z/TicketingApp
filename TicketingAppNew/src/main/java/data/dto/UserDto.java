package data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

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
}
