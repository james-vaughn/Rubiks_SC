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


    public Colors[] getTop() {
        return _colorsMatrix[0].clone();
    }

    public Colors[] getBottom() {
        return _colorsMatrix[Constants.DIMENSIONS-1].clone();
    }

    public Colors[] getLeft() {
        Colors[] retArray = new Colors[Constants.DIMENSIONS];
        for(int row = 0; row < Constants.DIMENSIONS; row++) {
            retArray[row] = _colorsMatrix[row][0];
        }

        return retArray;
    }

    public Colors[] getRight() {
        Colors[] retArray = new Colors[Constants.DIMENSIONS];
        for(int row = 0; row < Constants.DIMENSIONS; row++) {
            retArray[row] = _colorsMatrix[row][Constants.DIMENSIONS-1];
        }

        return retArray;
    }

    public Colors[][] getFullFace() {
        return _colorsMatrix.clone();
    }

    public void setTop(Colors[] newColors) {
        _colorsMatrix[0] = newColors.clone();
    }

    public void setBottom(Colors[] newColors) {
        _colorsMatrix[Constants.DIMENSIONS-1] = newColors.clone();
    }

    public void setLeft(Colors[] newColors) {
        for (int row = 0; row < Constants.DIMENSIONS; row++) {
            _colorsMatrix[row][0] = newColors[row];
        }
    }

    public void setRight(Colors[] newColors) {
        for (int row = 0; row < Constants.DIMENSIONS; row++) {
            _colorsMatrix[row][Constants.DIMENSIONS-1] = newColors[row];
        }
    }
}
