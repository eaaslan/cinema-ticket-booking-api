package cinema;

public class TicketWithToken {
    private Seat ticket;
    private String token;

    public TicketWithToken(Seat ticket, String token) {
        this.ticket = ticket;
        this.token = token;
    }

    // Getters and setters
    public Seat getTicket() {
        return ticket;
    }
    public void setTicket(Seat ticket) {
        this.ticket = ticket;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
}
