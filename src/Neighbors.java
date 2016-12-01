import java.util.ArrayList;
import java.util.HashMap;

public final class Neighbors {

    private static Neighbors _neighborsInstance = new Neighbors();
    private HashMap<Side, ArrayList<Coordinate>> _neighboringCoordinates = new HashMap<>();


    private Neighbors() {
        initCoordinates();
    }

    public static Neighbors getInstance() {
        return _neighborsInstance;
    }


    private void initCoordinates() {
        /*
        FaceNeighbors are coordinates of the neighboring faces starting
        above the coordinate (0, 0) of the original face and moving clockwise
        around the original face
        */

        // Neighbors for FRONT
        ArrayList<Coordinate> frontFaceNeighbors = new ArrayList<>();
        frontFaceNeighbors.add(new Coordinate(2, 0, Side.TOP));
        frontFaceNeighbors.add(new Coordinate(2, 1, Side.TOP));
        frontFaceNeighbors.add(new Coordinate(2, 2, Side.TOP));
        frontFaceNeighbors.add(new Coordinate(0, 0, Side.RIGHT));
        frontFaceNeighbors.add(new Coordinate(1, 0, Side.RIGHT));
        frontFaceNeighbors.add(new Coordinate(2, 0, Side.RIGHT));
        frontFaceNeighbors.add(new Coordinate(0, 2, Side.BOTTOM));
        frontFaceNeighbors.add(new Coordinate(0, 1, Side.BOTTOM));
        frontFaceNeighbors.add(new Coordinate(0, 0, Side.BOTTOM));
        frontFaceNeighbors.add(new Coordinate(2, 2, Side.LEFT));
        frontFaceNeighbors.add(new Coordinate(1, 2, Side.LEFT));
        frontFaceNeighbors.add(new Coordinate(0, 2, Side.LEFT));

        _neighboringCoordinates.put(Side.FRONT, frontFaceNeighbors);

        // Neighbors for TOP
        ArrayList<Coordinate> topFaceNeighbors = new ArrayList<>();
        topFaceNeighbors.add(new Coordinate(0, 2, Side.BACK));
        topFaceNeighbors.add(new Coordinate(0, 1, Side.BACK));
        topFaceNeighbors.add(new Coordinate(0, 0, Side.BACK));
        topFaceNeighbors.add(new Coordinate(0, 2, Side.RIGHT));
        topFaceNeighbors.add(new Coordinate(0, 1, Side.RIGHT));
        topFaceNeighbors.add(new Coordinate(0, 0, Side.RIGHT));
        topFaceNeighbors.add(new Coordinate(0, 2, Side.FRONT));
        topFaceNeighbors.add(new Coordinate(0, 1, Side.FRONT));
        topFaceNeighbors.add(new Coordinate(0, 0, Side.FRONT));
        topFaceNeighbors.add(new Coordinate(0, 2, Side.LEFT));
        topFaceNeighbors.add(new Coordinate(0, 1, Side.LEFT));
        topFaceNeighbors.add(new Coordinate(0, 0, Side.LEFT));

        _neighboringCoordinates.put(Side.TOP, topFaceNeighbors);


        // Neighbors for RIGHT
        ArrayList<Coordinate> rightFaceNeighbors = new ArrayList<>();
        rightFaceNeighbors.add(new Coordinate(2, 2, Side.TOP));
        rightFaceNeighbors.add(new Coordinate(1, 2, Side.TOP));
        rightFaceNeighbors.add(new Coordinate(0, 2, Side.TOP));
        rightFaceNeighbors.add(new Coordinate(0, 0, Side.BACK));
        rightFaceNeighbors.add(new Coordinate(1, 0, Side.BACK));
        rightFaceNeighbors.add(new Coordinate(2, 0, Side.BACK));
        rightFaceNeighbors.add(new Coordinate(2, 2, Side.BOTTOM));
        rightFaceNeighbors.add(new Coordinate(1, 2, Side.BOTTOM));
        rightFaceNeighbors.add(new Coordinate(0, 2, Side.BOTTOM));
        rightFaceNeighbors.add(new Coordinate(2, 2, Side.FRONT));
        rightFaceNeighbors.add(new Coordinate(1, 2, Side.FRONT));
        rightFaceNeighbors.add(new Coordinate(0, 2, Side.FRONT));

        _neighboringCoordinates.put(Side.RIGHT, rightFaceNeighbors);

        // Neighbors for BOTTOM
        ArrayList<Coordinate> bottomFaceNeighbors = new ArrayList<>();
        bottomFaceNeighbors.add(new Coordinate(2, 0, Side.FRONT));
        bottomFaceNeighbors.add(new Coordinate(2, 1, Side.FRONT));
        bottomFaceNeighbors.add(new Coordinate(2, 2, Side.FRONT));
        bottomFaceNeighbors.add(new Coordinate(2, 0, Side.RIGHT));
        bottomFaceNeighbors.add(new Coordinate(2, 1, Side.RIGHT));
        bottomFaceNeighbors.add(new Coordinate(2, 2, Side.RIGHT));
        bottomFaceNeighbors.add(new Coordinate(2, 0, Side.BACK));
        bottomFaceNeighbors.add(new Coordinate(2, 1, Side.BACK));
        bottomFaceNeighbors.add(new Coordinate(2, 2, Side.BACK));
        bottomFaceNeighbors.add(new Coordinate(2, 0, Side.LEFT));
        bottomFaceNeighbors.add(new Coordinate(2, 1, Side.LEFT));
        bottomFaceNeighbors.add(new Coordinate(2, 2, Side.LEFT));

        _neighboringCoordinates.put(Side.BOTTOM, bottomFaceNeighbors);


        // Neighbors for LEFT
        ArrayList<Coordinate> leftFaceNeighbors = new ArrayList<>();
        leftFaceNeighbors.add(new Coordinate(0, 0, Side.TOP));
        leftFaceNeighbors.add(new Coordinate(1, 0, Side.TOP));
        leftFaceNeighbors.add(new Coordinate(2, 0, Side.TOP));
        leftFaceNeighbors.add(new Coordinate(0, 0, Side.FRONT));
        leftFaceNeighbors.add(new Coordinate(1, 0, Side.FRONT));
        leftFaceNeighbors.add(new Coordinate(2, 0, Side.FRONT));
        leftFaceNeighbors.add(new Coordinate(0, 0, Side.BOTTOM));
        leftFaceNeighbors.add(new Coordinate(1, 0, Side.BOTTOM));
        leftFaceNeighbors.add(new Coordinate(2, 0, Side.BOTTOM));
        leftFaceNeighbors.add(new Coordinate(2, 2, Side.BACK));
        leftFaceNeighbors.add(new Coordinate(1, 2, Side.BACK));
        leftFaceNeighbors.add(new Coordinate(0, 2, Side.BACK));

        _neighboringCoordinates.put(Side.LEFT, leftFaceNeighbors);


        // Neighbors for BACK
        ArrayList<Coordinate> backFaceNeighbors = new ArrayList<>();
        backFaceNeighbors.add(new Coordinate(0, 2, Side.TOP));
        backFaceNeighbors.add(new Coordinate(0, 1, Side.TOP));
        backFaceNeighbors.add(new Coordinate(0, 0, Side.TOP));
        backFaceNeighbors.add(new Coordinate(0, 0, Side.LEFT));
        backFaceNeighbors.add(new Coordinate(1, 0, Side.LEFT));
        backFaceNeighbors.add(new Coordinate(2, 0, Side.LEFT));
        backFaceNeighbors.add(new Coordinate(2, 0, Side.BOTTOM));
        backFaceNeighbors.add(new Coordinate(2, 1, Side.BOTTOM));
        backFaceNeighbors.add(new Coordinate(2, 2, Side.BOTTOM));
        backFaceNeighbors.add(new Coordinate(2, 2, Side.RIGHT));
        backFaceNeighbors.add(new Coordinate(1, 2, Side.RIGHT));
        backFaceNeighbors.add(new Coordinate(0, 2, Side.RIGHT));

        _neighboringCoordinates.put(Side.BACK, backFaceNeighbors);
    }


    public ArrayList<Coordinate> getNeighbors(Side faceLocation) {
        return _neighboringCoordinates.get(faceLocation);
    }
}