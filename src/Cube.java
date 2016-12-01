import java.util.*;

public final class Cube{

    HashMap<Side, Face> _cubeFaces = new HashMap<>();

    //Default to a full solved cube
    public Cube() {
        _cubeFaces.put(Side.TOP, new Face(Colors.BLUE));
        _cubeFaces.put(Side.BOTTOM, new Face(Colors.GREEN));
        _cubeFaces.put(Side.FRONT, new Face(Colors.ORANGE));
        _cubeFaces.put(Side.BACK, new Face(Colors.RED));
        _cubeFaces.put(Side.LEFT, new Face(Colors.YELLOW));
        _cubeFaces.put(Side.RIGHT, new Face(Colors.WHITE));
    }

    //TODO barricade
    public Cube(List<Colors[][]> faceColors) {

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
        printLayout.add(Arrays.asList(Side.FRONT,Side.RIGHT,Side.BACK,Side.LEFT));

        //bottom row
        printLayout.add(Arrays.asList(null, null, Side.BOTTOM, null));

        outputCube(printLayout);
    }

    private void outputCube(List<List<Side>> printLayout) {
        for (int pictureHeight = 0; pictureHeight < Constants.PICTURE_HEIGHT; pictureHeight++) {
            for (int faceHeight = 0; faceHeight < Constants.DIMENSIONS; faceHeight++) {
                for (int pictureWidth = 0; pictureWidth <Constants.PICTURE_WIDTH; pictureWidth++) {

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


    //handles manipulation of the cube
    private static class Rotator {

    }
}
