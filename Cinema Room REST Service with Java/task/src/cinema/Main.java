package cinema;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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


    public ApiController() {
        this.seats = initializeSeats();
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
        return ResponseEntity.ok(seat);

    }

    public Seat findSeat(int row, int column) {
        return seats.stream()
                .filter(s -> s.getRow() == row && s.getColumn() == column)
                .findFirst()
                .orElse(null);
    }
}


