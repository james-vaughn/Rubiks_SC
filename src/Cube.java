import java.util.*;

public final class Cube{

    private HashMap<Side, Face> _cubeFaces = new HashMap<>();
    private Rotator _rotator = new Rotator();

    //Default to a full solved cube
    public Cube() {
        _cubeFaces.put(Side.TOP, new Face(Colors.BLUE));
        _cubeFaces.put(Side.BOTTOM, new Face(Colors.GREEN));
        _cubeFaces.put(Side.FRONT, new Face(Colors.ORANGE));
        _cubeFaces.put(Side.BACK, new Face(Colors.RED));
        _cubeFaces.put(Side.LEFT, new Face(Colors.YELLOW));
        _cubeFaces.put(Side.RIGHT, new Face(Colors.WHITE));
    }

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


    private void validateCubeLayout(List<Colors[][]> faceColors) {

        if( faceColors.size() != Constants.SIDES_ON_A_CUBE ) {
            ErrorLogger.getInstance().die("Not enough sides provided");
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
    
    private boolean areCentersUnique(List<Colors[][]> faceColors) {
        HashSet<Colors> usedCenters = new HashSet<>();

        for (Colors[][] faceMatrix : faceColors) {
            if( !usedCenters.add(faceMatrix[Constants.DIMENSIONS/2][Constants.DIMENSIONS/2]) ) {
                return false;
            }
        }
        return true;
    }
    
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

    private boolean hasCorrectDimensions(List<Colors[][]> faceColors) {
        for(Colors[][] faceMatrix : faceColors) {
            if(faceMatrix.length != Constants.DIMENSIONS) return false;

            for(int row = 0; row < Constants.DIMENSIONS; row++) {

                if(faceMatrix[row].length != Constants.DIMENSIONS) return false;
            }
        }

        return true;
    }


    //formatting:
    //              Top
    //Front Right   Back	Left
    //              Bottom
    public void prettyPrint() {
        List<List<Side>> printLayout = new ArrayList<>();

        //nulls indicate a blank face to print for formatting purposes

        //top row
        printLayout.add(Arrays.asList(null, null, Side.TOP, null));

        //middle row
        printLayout.add(Arrays.asList(Side.BACK,Side.LEFT,Side.FRONT,Side.RIGHT));

        //bottom row
        printLayout.add(Arrays.asList(null, null, Side.BOTTOM, null));

        outputCube(printLayout);
    }

    //complexity of 4
    private void outputCube(List<List<Side>> printLayout) {
        for (int pictureHeight = 0; pictureHeight < Constants.PICTURE_HEIGHT; pictureHeight++) {
            for (int faceHeight = 0; faceHeight < Constants.DIMENSIONS; faceHeight++) { //go through the image lines
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

    private void printBlankLine() {
        System.out.print("    ");
    }

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


    public void rotate(Side side, Direction direction) {
        Objects.requireNonNull(side);
        Objects.requireNonNull(direction);

        if( direction != Direction.CLOCKWISE && direction != Direction.COUNTER_CLOCKWISE ) {
            ErrorLogger.getInstance().die("You cannot rotate the cube is that direction");
        }

        _rotator.rotate(side, direction);
    }


    //handles manipulation of the cube
    //Had to be changed from a static class in order to access the face table; there is no other way
    private class Rotator {

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
            Colors[] tempColors = face.getTopRow();

            face.setTop( reverseColors(face.getLeft()) );
            face.setLeft(face.getBottomRow());
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
            Face tempFace = _cubeFaces.get(side);
            Colors[] tempColors = tempFace.getTopRow();


        }
    }
}
