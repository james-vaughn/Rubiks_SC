import java.util.Objects;

public class Coordinate {

    private int row;
    private int col;
    private Colors color;
    private Side side;

    /*
    // Used to specify a face's inner perimeter coordinates
    public Coordinate(int rowNumber, int columnNumber) {
        row = rowNumber;
        col = columnNumber;
    }

    // Used to get and set squares on a cube
    public Coordinate(int rowNumber, int columnNumber, Colors colorOfCoord) {
        row = rowNumber;
        col = columnNumber;
        color = colorOfCoord;
    }*/

    // Used in Neighbors class to specify a face's neighboring coordinates
    public Coordinate(int rowNumber, int colNumber, Side sideOfCoord) {

        Objects.requireNonNull(sideOfCoord,
                "side must not be null");
        row = rowNumber;
        col = colNumber;
        side = sideOfCoord;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Colors getColor() {
        return color;
    }

    public Side getSide() {
        return side;
    }
}
