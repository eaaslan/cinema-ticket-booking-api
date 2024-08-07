package cinema;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}

@RestController
class ApiController{

private static final int ROWS = 9;
private static final int COLUMNS = 9;
private final List<Seat> seats ;
private final Map<String,Seat> tokenToSeat = new HashMap<>();


public ApiController() {
        this.seats = initializeSeats();
        Statistics.initializeSeats(ROWS * COLUMNS);

}
    private List<Seat> initializeSeats() {
        List<Seat> seats = new ArrayList<>();
        for (int i = 1; i <= ROWS; i++) {
            for (int j = 1; j <= COLUMNS; j++) {
                seats.add(new Seat(i, j));
            }
        }
        return seats;
    }

    @GetMapping("/seats")
    public SeatsResponse getSeats() {
        return new SeatsResponse(ROWS, COLUMNS, seats);
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseTicket(@RequestBody SeatRequest seatRequest) {

        int row = seatRequest.getRow();
        int column = seatRequest.getColumn();


        if (row < 1 || row > ROWS || column < 1 || column > COLUMNS) {
            ErrorResponse error = new ErrorResponse("The number of a row or a column is out of bounds!");
            return ResponseEntity.badRequest().body(error);
        }

        Seat seat = findSeat(row, column);

        if (seat.isBooked()) {
            ErrorResponse error = new ErrorResponse("The ticket has been already purchased!");
            return ResponseEntity.badRequest().body(error);
        }

        seat.setBooked(true);
        UUID uuid = UUID.randomUUID();
        Token token = new Token(uuid.toString());
        tokenToSeat.put(uuid.toString(),seat);
        TicketWithToken ticketWithToken = new TicketWithToken(seat,token.getToken());
        Statistics.updateOnPurchase(seat.getPrice());
        return ResponseEntity.ok(ticketWithToken);

    }

    @PostMapping("/return")
    public ResponseEntity<?>returnTicket(@RequestBody Token token) {

        Seat seat = tokenToSeat.remove(token.getToken());


        if (seat == null) {
            ErrorResponse error = new ErrorResponse("Wrong token!");
            return ResponseEntity.badRequest().body(error);
        }
        seat.setBooked(false);
        Statistics.updateOnReturn(seat.getPrice());

        Map<String,Seat> response = new HashMap<>();
        response.put("ticket" , seat)   ;

        return ResponseEntity.ok(response);
    }

    @GetMapping("/stats")
    public ResponseEntity<?> getStats(@RequestParam(required = false) String password) {
        if (password == null || !password.equals("super_secret")) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "The password is wrong!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }

        return ResponseEntity.ok(Statistics.getStatistics());
    }





    public Seat findSeat(int row, int column) {
        return seats.stream()
                .filter(s -> s.getRow() == row && s.getColumn() == column)
                .findFirst()
                .orElse(null);
    }
}


