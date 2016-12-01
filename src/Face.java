import java.util.Objects;

public class Face {

    Colors[][] _colorsMatrix = new Colors[Constants.DIMENSIONS][Constants.DIMENSIONS];

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

}
