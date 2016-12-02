import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class FaceTest {

    private Colors[][] _colorsMatrix;
    private Face _face, _mutatingFace;

    @Before
    public void SetUp() {
        final Colors[][] faceMat = {
                                    {Colors.RED, Colors.GREEN, Colors.BLUE},
                                    {Colors.RED, Colors.RED, Colors.ORANGE},
                                    {Colors.YELLOW, Colors.WHITE, Colors.ORANGE}
                                    };

        _colorsMatrix = faceMat.clone();

        _face = new Face(_colorsMatrix);
        _mutatingFace = new Face(_colorsMatrix);
    }


    //getColorLabel


    //structured basis
    //full coverage
    @Test
    public void Should_return_the_first_letter_of_the_color_as_label() {
        assertEquals('R',_face.getColorLabel(0,0));
        assertEquals('B',_face.getColorLabel(0,2));
        assertEquals('W',_face.getColorLabel(2,1));
    }

    //bad data
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void Should_error_when_bad_bounds_are_chosen() {
        _face.getColorLabel(Constants.DIMENSIONS, Constants.DIMENSIONS);
    }


    //tests not needed for the for loops not running as the constant wont change and is a natural #


    //getTop


    //structured basis
    //full coverage
    @Test
    public void Should_return_the_top_row_of_colors(){
        assertArrayEquals(_colorsMatrix[0], _face.getTop());
    }


    //getBottom


    //structured basis
    //full coverage
    @Test
    public void Should_return_the_bottom_row_of_colors(){
        assertArrayEquals(_colorsMatrix[Constants.DIMENSIONS-1], _face.getBottom());
    }


    //getLeft


    //structured basis
    //full coverage
    @Test
    public void Should_return_the_left_col_of_colors() {
        Colors[] correctArray = {Colors.RED, Colors.RED, Colors.YELLOW};
        assertArrayEquals(correctArray, _face.getLeft());
    }


    //getRight


    //structured basis
    //full coverage
    @Test
    public void Should_return_the_right_col_of_colors() {
        Colors[] correctArray = {Colors.BLUE, Colors.ORANGE, Colors.ORANGE};
        assertArrayEquals(correctArray, _face.getRight());
    }


    //getFullFace


    //structure basis
    //full coverage
    @Test
    public void Should_return_full_face_of_colors() {
        assertArrayEquals(_colorsMatrix, _face.getFullFace());
    }


    //setTop


    //structured basis
    @Test
    public void Should_set_top_row() {
        Colors[] newArr = {Colors.ORANGE, Colors.RED, Colors.ORANGE};

        assertArrayEquals(_colorsMatrix[0], _mutatingFace.getTop());

        _mutatingFace.setTop(newArr);
        assertArrayEquals(newArr, _mutatingFace.getTop());
    }


    //setTop


    //structured basis
    @Test
    public void Should_set_bottom_row() {
        Colors[] newArr = {Colors.RED, Colors.WHITE, Colors.BLUE};

        assertArrayEquals(_colorsMatrix[2], _mutatingFace.getBottom());

        _mutatingFace.setBottom(newArr);
        assertArrayEquals(newArr, _mutatingFace.getBottom());
    }


    //setTop


    //structured basis
    @Test
    public void Should_set_left_col() {
        Colors[] correctArray = {Colors.RED, Colors.RED, Colors.YELLOW};
        Colors[] newArr = {Colors.RED, Colors.RED, Colors.RED};

        assertArrayEquals(correctArray, _mutatingFace.getLeft());

        _mutatingFace.setLeft(newArr);
        assertArrayEquals(newArr, _mutatingFace.getLeft());
    }


    //setTop


    //structured basis
    @Test
    public void Should_set_right_col() {
        Colors[] correctArray = {Colors.BLUE, Colors.ORANGE, Colors.ORANGE};
        Colors[] newArr = {Colors.RED, Colors.RED, Colors.BLUE};

        assertArrayEquals(correctArray, _mutatingFace.getRight());

        _mutatingFace.setRight(newArr);
        assertArrayEquals(newArr, _mutatingFace.getRight());
    }
}
