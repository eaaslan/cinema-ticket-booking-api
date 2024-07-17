package cinema;

public class Seat {
    private int row;
    private int column;
    private int price;
    private boolean isBooked = false;

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        this.price = row <= 4 ? 10 : 8;
        this.isBooked = false;
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

    public void setBooked(boolean b) {
        this.isBooked = b;
    }

    public boolean isBooked() {
        return isBooked;
    }
}
