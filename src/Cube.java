import java.util.*;

/**
Represents the Rubiks cube and serves as an intermediary between the user and the rotator
 */
public final class Cube{

    /**
    Map of the side values to a face instance
     */
    private HashMap<Side, Face> _cubeFaces = new HashMap<>();

    /**
    Rotator instance for performing cube manipulations
     */
    private Rotator _rotator = new Rotator();

    /**
    Default constructor for a RUbiks cube
    Default to a full solved cube
     */
    public Cube() {
        _cubeFaces.put(Side.TOP, new Face(Colors.BLUE));
        _cubeFaces.put(Side.BOTTOM, new Face(Colors.GREEN));
        _cubeFaces.put(Side.FRONT, new Face(Colors.ORANGE));
        _cubeFaces.put(Side.BACK, new Face(Colors.RED));
        _cubeFaces.put(Side.LEFT, new Face(Colors.YELLOW));
        _cubeFaces.put(Side.RIGHT, new Face(Colors.WHITE));
    }

    /**
    Alternate constructor which takes a list of color matrices and returns a Cube object if it is valid;
    @param faceColors A list of color matrices which correspond to the faces to construct. The list is of order:
                      TOP, BOTTOM, FRONT, BACK, LEFT, RIGHT
     */
    public Cube(List<Colors[][]> faceColors) {

        //barricade
        validateCubeLayout(faceColors);

        _cubeFaces.put(Side.TOP, new Face(faceColors.get(0)));
        _cubeFaces.put(Side.BOTTOM, new Face(faceColors.get(1)));
        _cubeFaces.put(Side.FRONT, new Face(faceColors.get(2)));
        _cubeFaces.put(Side.BACK, new Face(faceColors.get(3)));
        _cubeFaces.put(Side.LEFT, new Face(faceColors.get(4)));
        _cubeFaces.put(Side.RIGHT, new Face(faceColors.get(5)));
    }


    /**
    Checks if the faces feed into it compose a valid rubiks cube.
    Criteria: Number of faces, dimensions of the faces, amount of each square color, center uniqueness
    @param faceColors A list of colors which correspond to the faces to construct.
     */
    private void validateCubeLayout(List<Colors[][]> faceColors) {

        Objects.requireNonNull(faceColors);

        if( faceColors.size() != Constants.SIDES_ON_A_CUBE ) {
            ErrorLogger.getInstance().die("Wrong number of sides provided");
        }

        if( !areCentersUnique(faceColors) ) {
            ErrorLogger.getInstance().die("Center pieces have repeats");
        }
        
        if( !hasCorrectColorCounts(faceColors) ) {
            ErrorLogger.getInstance().die("Wrong number of each square color present");
        }

        if( !hasCorrectDimensions(faceColors) ) {
            ErrorLogger.getInstance().die("Bad dimensions for input");
        }
    }

    /**
    Returns whether or not the center squares of the faces to construct are unique
    @param faceColors A list of colors which correspond to the faces to construct.
    @return boolean which is true if the centers are all unique and false if a repeat center color is found
     */
    private boolean areCentersUnique(List<Colors[][]> faceColors) {
        HashSet<Colors> usedCenters = new HashSet<>();

        for (Colors[][] faceMatrix : faceColors) {
            if( !usedCenters.add(faceMatrix[Constants.DIMENSIONS/2][Constants.DIMENSIONS/2]) ) {
                return false;
            }
        }
        return true;
    }

    /**
    Returns whether or not the cube to construct has the right number of each color (1 full face worth of each color)
    @param faceColors A list of colors which correspond to the faces to construct.
    @return boolean which is true if the cube has the correct number of each color or false otherwise
     */
    private boolean hasCorrectColorCounts(List<Colors[][]> faceColors) {
        HashMap<Colors, Integer> colorCounts = colorCountsMap(faceColors);

        for(Integer count : colorCounts.values()) {

            //if we dont have exactly 1 full face's worth of a color, fail
            if( count != (Constants.DIMENSIONS * Constants.DIMENSIONS) ) {
                return false;
            }
        }

        return true;
    }

    /**
    Creates a map of cube colors to their occurance count; acts as a counter for helping hasCorrectColorCounts
    @param faceColors A list of colors which correspond to the faces to construct.
    @return a map of colors to their # of appearances
     */
    private HashMap<Colors, Integer> colorCountsMap(List<Colors[][]> faceColors) {
        HashMap<Colors, Integer> colorCounts = new HashMap<>();

        for(Colors[][] faceMatrix : faceColors) {

            for(int row = 0; row < Constants.DIMENSIONS; row++) {
                for (int col = 0; col < Constants.DIMENSIONS; col++) {

                    Colors squareColor = faceMatrix[row][col];
                    Objects.requireNonNull(squareColor);

                    Integer oldCount = colorCounts.get(squareColor);

                    //if the color has not been found before, its count should be 0
                    if( oldCount == null ) oldCount = 0;

                    colorCounts.put(squareColor, oldCount + 1);

                }
            }

        }

        return colorCounts;
    }

    /**
    Returns whether or not the cube to construct has the proper dimensions for each face
    @param faceColors A list of colors which correspond to the faces to construct.
    @return boolean which is true if the faces are all of the correct dimension (Dimension x Dimension)
                      and is false if any of the faces are of the wrong dimensions
     */
    private boolean hasCorrectDimensions(List<Colors[][]> faceColors) {
        for(Colors[][] faceMatrix : faceColors) {
            if(faceMatrix.length != Constants.DIMENSIONS) return false;

            for(int row = 0; row < Constants.DIMENSIONS; row++) {

                if(faceMatrix[row].length != Constants.DIMENSIONS) return false;
            }
        }

        return true;
    }


    /**
    Prints out the cube in a readable format:

    formatting:
                  Top
    Back   Left   Front   Right
                  Bottom

    Will print out the 1st letter of the squares color at each position
    */
    public void prettyPrint() {
        List<List<Side>> printLayout = new ArrayList<>();

        //nulls indicate a blank face to print for formatting purposes

        //top row
        printLayout.add(Arrays.asList(null, null, Side.TOP, null));

        //middle row
        printLayout.add(Arrays.asList(Side.BACK,Side.LEFT,Side.FRONT,Side.RIGHT));

        //bottom row
        printLayout.add(Arrays.asList(null, null, Side.BOTTOM, null));

        outputSides(printLayout);
    }

    /**
    Outputs line-by-line the cube colors according to a given layout

    @param printLayout List of a list of sides of the cube to print;
           the inner list represents the width of the layout
           the outer list represents the heignt of the layout.
           Works like a side matrix
     */
    private void outputSides(List<List<Side>> printLayout) {
        for (int pictureHeight = 0; pictureHeight < Constants.PICTURE_HEIGHT; pictureHeight++) {
            //go through the image lines
            for (int faceHeight = 0; faceHeight < Constants.DIMENSIONS; faceHeight++) {
                for (int pictureWidth = 0; pictureWidth < Constants.PICTURE_WIDTH; pictureWidth++) {

                    Side sideToPrint = printLayout.get(pictureHeight).get(pictureWidth);
                    if (sideToPrint != null) {
                        printFaceLine(sideToPrint, faceHeight);
                    } else {
                        printBlankLine();
                    }
                }

                System.out.print("\n");
            }
        }
    }

    /**
    Outputs a blank line of a face for the purposes of formatting
     */
    private void printBlankLine() {
        System.out.print("    ");
    }

    /**
    Outputs the color initial of each color in a given row of a given face

    @param side What side of the cube of which we are printing the colors
    @param row  What row of the given side we are printing
     */
    private void printFaceLine(Side side, int row) {
        Face face = _cubeFaces.get(side);
        StringBuilder outputLine = new StringBuilder();

        for (int i = 0; i < Constants.DIMENSIONS; i++) {
            char colorLabel = face.getColorLabel(row,i);
            outputLine.append(colorLabel);
        }

        outputLine.append(' ');

        System.out.print(outputLine.toString());
    }


    /**
     * Performs a rotation to one of the faces of the rubiks cube and cascades the change to the side's neighbors.
     * Delegates to the Rotator after verifying the input.
     @param side Side of the cube to rotate
     @param direction Direction of rotation. Must be clockwise or counter-clockwise
     */
    public void rotate(Side side, Direction direction) {
        Objects.requireNonNull(side);
        Objects.requireNonNull(direction);

        //barricade
        if( direction != Direction.CLOCKWISE && direction != Direction.COUNTER_CLOCKWISE ) {
            ErrorLogger.getInstance().die("You cannot rotate the cube is that direction");
        }

        _rotator.rotate(side, direction);
    }

    /**
     * Handles manipulation of the cube
     * Had to be changed from a static class in order to access the face table; there is no other way
     */
    private class Rotator {

        /**
         * Performs a rotation to one of the faces of the rubiks cube and cascades the change to the side's neighbors.
         @param side Side of the cube to rotate
         @param direction Direction of rotation. Must be clockwise or counter-clockwise
         */
        public void rotate(Side side, Direction direction) {

            switch (direction) {

                case CLOCKWISE:
                    rotateClockwise(side);
                    break;

                case COUNTER_CLOCKWISE:
                    for (int i = 0; i < Constants.ROTATIONS_TO_MAKE_A_COUNTER_CLOCKWISE_TURN; i++) {
                        rotateClockwise(side);
                    }
                    break;
            }

        }

        private void rotateClockwise(Side side) {
            rotateFace(side);
            adjustNeighbors(side);
        }

        //1 2 3       7 4 1  <-- reverse of left
        //4 5 6  ==>  8 5 2
        //7 8 9       9 6 3  <-- reverse of right
        private void rotateFace(Side side) {
            Face face = _cubeFaces.get(side);
            Colors[] tempColors = face.getTop();

            face.setTop( reverseColors(face.getLeft()) );
            face.setLeft(face.getBottom());
            face.setBottom( reverseColors(face.getRight()) );
            face.setRight(tempColors);
        }

        private Colors[] reverseColors(Colors[] colors) {
            Colors[] reverse = new Colors[colors.length];

            for(int index = 0; index < colors.length; index++) {
                reverse[index] = colors[ (colors.length - index) - 1 ];
            }

            return reverse;
        }

        private void adjustNeighbors(Side side) {

            List<Pair<Direction,Side>> mapping = Neighbors.NeighborMappings.get(side);
            Pair<Direction,Side> tempNeighbor = mapping.get(0); //grab 1st neighbor to save its colors

            //keep the 1st set of colors to set at the end
            Colors[] tempColors = colorsFromNeighbor(tempNeighbor.first(), _cubeFaces.get(tempNeighbor.second()));

            for(int index = mapping.size() - 1; index >= 1; index--) {
                tempNeighbor = mapping.get(index);
                Colors[] replacementColors = colorsFromNeighbor(tempNeighbor.first(), _cubeFaces.get(tempNeighbor.second()));

                tempNeighbor = mapping.get((index + 1) % (mapping.size()) ); //4 wraps to 0
                setNeighborsColors(tempNeighbor.first(), _cubeFaces.get(tempNeighbor.second()), replacementColors);
            }

            //manually set the 2nd side's colors to the original 1st side's colors (now the original 4th's)
            tempNeighbor = mapping.get(1);
            setNeighborsColors(tempNeighbor.first(), _cubeFaces.get(tempNeighbor.second()), tempColors);
        }

        //complexity 4
        private Colors[] colorsFromNeighbor(Direction direction, Face neighbor) {
            Colors[] colors = new Colors[Constants.DIMENSIONS];

            switch (direction) {
                case TOP:
                    colors = neighbor.getTop();
                    break;

                case RIGHT:
                    colors = neighbor.getRight();
                    break;

                case BOTTOM:
                    colors = neighbor.getBottom();
                    break;

                case LEFT:
                    colors = neighbor.getLeft();
                    break;

                default:
                    ErrorLogger.getInstance().die("Neighbor mappings contain bad directions");
            }

            return colors;
        }

        //complexity 4
        private void setNeighborsColors(Direction direction, Face neighbor, Colors[] replacement) {
            switch (direction) {
                case TOP:
                    neighbor.setTop(replacement);
                    break;

                case RIGHT:
                    neighbor.setRight(replacement);
                    break;

                case BOTTOM:
                    neighbor.setBottom(replacement);
                    break;

                case LEFT:
                    neighbor.setLeft(replacement);
                    break;

                default:
                    ErrorLogger.getInstance().die("Neighbor mappings contain bad directions");
            }
        }

        public class Exposer {

            public void rotateFaceExposed(Side side) {
                rotateFace(side);
            }

            public Colors[] reverseColorsExposed(Colors[] colors) {
                return reverseColors(colors);
            }

            public void adjustNeighborsExposed(Side side) {
                adjustNeighbors(side);
            }

            public Colors[] colorsFromNeighborExposed(Direction direction, Face neighbor) {
                return colorsFromNeighbor(direction, neighbor);
            }

            public void setNeighborsColorsExposed(Direction direction, Face neighbor, Colors[] replacement) {
                setNeighborsColors(direction, neighbor, replacement);
            }
        }
    }

    public class Exposer {

        private Rotator.Exposer _rotatorExposer = _rotator. new Exposer();

        public void validateCubeLayoutExposed(List<Colors[][]> faceColors) {
            validateCubeLayout(faceColors);
        }

        public boolean areCentersUniqueExposed(List<Colors[][]> faceColors) {
            return areCentersUnique(faceColors);
        }

        public boolean hasCorrectColorCountsExposed(List<Colors[][]> faceColors) {
            return hasCorrectColorCounts(faceColors);
        }

        public boolean hasCorrectDimensionsExposed(List<Colors[][]> faceColors) {
            return hasCorrectDimensions(faceColors);
        }

        public void outputSidesExposed(List<List<Side>> printLayout) {
            outputSides(printLayout);
        }

        public void printBlankLineExposed() {
            printBlankLine();
        }

        public void printFaceLineExposed(Side side, int row) {
            printFaceLine(side, row);
        }

        //calls to the rotator's exposer, since the unit tests cannot create a rotator exposer

        public void rotateFaceExposed(Side side) {
            _rotatorExposer.rotateFaceExposed(side);
        }

        public Face getFace(Side side) {
            return _cubeFaces.get(side);
        }

        public Colors[] reverseColorsExposed(Colors[] colors) {
            return _rotatorExposer.reverseColorsExposed(colors);
        }

        public void adjustNeighborsExposed(Side side) {
            _rotatorExposer.adjustNeighborsExposed(side);
        }

        public Colors[] colorsFromNeighborExposed(Direction direction, Face neighbor) {
            return _rotatorExposer.colorsFromNeighborExposed(direction, neighbor);
        }

        public void setNeighborsColorsExposed(Direction direction, Face neighbor, Colors[] replacement) {
            _rotatorExposer.setNeighborsColorsExposed(direction, neighbor, replacement);
        }
    }

}