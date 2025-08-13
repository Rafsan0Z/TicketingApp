package data.dto;

public class TicketInfo {
    private String email;
    private long ticketId;

    public TicketInfo() {}
    public TicketInfo(String email, long ticketId) {
        this.email = email;
        this.ticketId = ticketId;
    }

    // Getters and setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getTicketId() {
        return ticketId;
    }

    public void setTicketId(long ticketId) {
        this.ticketId = ticketId;
    }
    
}
