package cinema;

public class SeatRequest {


    private int row;
    private int column;

    public SeatRequest() {
    }

    public SeatRequest(int row, int column) {
        this.row = row;
        this.column = column;
    }

    // Getters and setters
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}