import java.util.Objects;

public class Face {

    private Colors[][] _colorsMatrix = new Colors[Constants.DIMENSIONS][Constants.DIMENSIONS];

    //create a solid colored face
    public Face(Colors color)  {
        Objects.requireNonNull(color); //enforce the color is valid

        //set all elements of the color matrix to the selected color
        for (int row = 0; row < _colorsMatrix.length; row++) {
            for (int col = 0; col < _colorsMatrix.length; col++) {
                _colorsMatrix[row][col] = color;
            }
        }
    }

    //construct a face from a given matrix
    public Face(Colors[][] colors) {//cloning to prevent issues with reference manipulations
        _colorsMatrix = colors.clone();
    }

    //returns the front letter of the color for printing purposes
    public char getColorLabel(int row, int col) {
        Colors squareColor = _colorsMatrix[row][col];
        return squareColor.toString().charAt(0);
    }


    public Colors[] getTopRow() {
        return _colorsMatrix[0];
    }

    public Colors[] getBottomRow() {
        return _colorsMatrix[Constants.DIMENSIONS];
    }
}
