import java.util.Objects;

/*
Data class which stores info about colors on a single face of a cube;
Just a color matrix with some helpful methods for extracting and setting data
 */
public class Face {

    /*
    Color matrix which holds all of the square colors for the face
     */
    private Colors[][] _colorsMatrix = new Colors[Constants.DIMENSIONS][Constants.DIMENSIONS];

    /*
    Simple constructor which sets the face to a full color
     */
    public Face(Colors color)  {
        Objects.requireNonNull(color); //enforce the color is valid

        //set all elements of the color matrix to the selected color
        for (int row = 0; row < _colorsMatrix.length; row++) {
            for (int col = 0; col < _colorsMatrix.length; col++) {
                _colorsMatrix[row][col] = color;
            }
        }
    }

    /*
    Constructor to set the face based on a passed in color matrix
     */
    public Face(Colors[][] colors) {//cloning to prevent issues with reference manipulations
        _colorsMatrix = colors.clone();
    }

    /*
    returns the front letter of the color for printing purposes
    @param row What row the square is in
           col What column the square is in
    @return The first letter of the color located at the position indicated by the params
     */
    public char getColorLabel(int row, int col) {
        Colors squareColor = _colorsMatrix[row][col];
        return squareColor.toString().charAt(0);
    }

    /*
    @return A clone of the top row of the face
     */
    public Colors[] getTop() {
        return _colorsMatrix[0].clone();
    }

    /*
    @return A clone of the bottom row of the face
     */
    public Colors[] getBottom() {
        return _colorsMatrix[Constants.DIMENSIONS-1].clone();
    }

    /*
    @return A clone of the left column of the face
     */
    public Colors[] getLeft() {
        Colors[] retArray = new Colors[Constants.DIMENSIONS];
        for(int row = 0; row < Constants.DIMENSIONS; row++) {
            retArray[row] = _colorsMatrix[row][0];
        }

        return retArray;
    }

    /*
    @return A clone of the right column of the face
     */
    public Colors[] getRight() {
        Colors[] retArray = new Colors[Constants.DIMENSIONS];
        for(int row = 0; row < Constants.DIMENSIONS; row++) {
            retArray[row] = _colorsMatrix[row][Constants.DIMENSIONS-1];
        }

        return retArray;
    }

    /*
    @return A clone of the color matrix stored by the face
     */
    public Colors[][] getFullFace() {
        return _colorsMatrix.clone();
    }

    /*
    Sets the top of the face to a clone of the passed in color array
    @param newColors Color array to overwrite the old array
     */
    public void setTop(Colors[] newColors) {
        _colorsMatrix[0] = newColors.clone();
    }

    /*
    Sets the bottom of the face to a clone of the passed in color array
    @param newColors Color array to overwrite the old array
     */
    public void setBottom(Colors[] newColors) {
        _colorsMatrix[Constants.DIMENSIONS-1] = newColors.clone();
    }

    /*
    Sets the left column of the face to a clone of the passed in color array
    @param newColors Color array to overwrite the old array
     */
    public void setLeft(Colors[] newColors) {
        for (int row = 0; row < Constants.DIMENSIONS; row++) {
            _colorsMatrix[row][0] = newColors[row];
        }
    }

    /*
    Sets the right column of the face to a clone of the passed in color array
    @param newColors Color array to overwrite the old array
     */
    public void setRight(Colors[] newColors) {
        for (int row = 0; row < Constants.DIMENSIONS; row++) {
            _colorsMatrix[row][Constants.DIMENSIONS-1] = newColors[row];
        }
    }
}
