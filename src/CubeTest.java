import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CubeTest {

    private Cube _defaultCube,
                 _cube;

    List<Colors[][]> _cubeColors;

    private Cube.Exposer _cubeExposer, _defaultCubeExposer;
    private ByteArrayOutputStream _outText = new ByteArrayOutputStream();
    private ByteArrayOutputStream _errText = new ByteArrayOutputStream();

    @Before
    public void SetUp() {
        System.setOut(new PrintStream(_outText));
        System.setErr(new PrintStream(_errText));

        Colors[][] topFaceColors = {
                {Colors.YELLOW, Colors.WHITE, Colors.YELLOW},
                {Colors.GREEN, Colors.BLUE, Colors.ORANGE},
                {Colors.BLUE, Colors.YELLOW, Colors.WHITE}
        };

        Colors[][] bottomFaceColors = {
                {Colors.WHITE, Colors.WHITE, Colors.GREEN},
                {Colors.BLUE, Colors.GREEN, Colors.YELLOW},
                {Colors.GREEN, Colors.GREEN, Colors.YELLOW}
        };

        Colors[][] frontFaceColors = {
                {Colors.RED, Colors.RED, Colors.GREEN},
                {Colors.WHITE, Colors.ORANGE, Colors.ORANGE},
                {Colors.BLUE, Colors.RED, Colors.ORANGE}
        };

        Colors[][] backFaceColors = {
                {Colors.ORANGE, Colors.BLUE, Colors.ORANGE},
                {Colors.WHITE, Colors.RED, Colors.RED},
                {Colors.BLUE, Colors.ORANGE, Colors.RED}
        };

        Colors[][] leftFaceColors = {
                {Colors.BLUE, Colors.RED, Colors.WHITE},
                {Colors.BLUE, Colors.YELLOW, Colors.ORANGE},
                {Colors.YELLOW, Colors.YELLOW, Colors.ORANGE}
        };

        Colors[][] rightFaceColors = {
                {Colors.RED, Colors.BLUE, Colors.GREEN},
                {Colors.YELLOW, Colors.WHITE, Colors.GREEN},
                {Colors.WHITE, Colors.GREEN, Colors.RED}
        };

        _cubeColors = Arrays.asList(topFaceColors, bottomFaceColors, frontFaceColors, backFaceColors, leftFaceColors, rightFaceColors);
        _defaultCube = new Cube();
        _cube = new Cube(_cubeColors);
        _cubeExposer= _cube. new Exposer();
        _defaultCubeExposer = _defaultCube. new Exposer();
    }


    //constructor


    //structured basis
    //check that an invalid cube cannot be constructed
    @Test(expected = RuntimeException.class)
    public void Should_not_permit_invalid_cubes_to_be_constructed() {
        Cube cube = new Cube(new ArrayList<>());
    }


    //Constructors and prettyPrint


    //structured basis
    //full coverage
    @Test
    public void Should_display_expected_default_cube() {
        String expectOutput =
                        "        BBB     \n" +
                        "        BBB     \n" +
                        "        BBB     \n" +
                        "RRR YYY OOO WWW \n" +
                        "RRR YYY OOO WWW \n" +
                        "RRR YYY OOO WWW \n" +
                        "        GGG     \n" +
                        "        GGG     \n" +
                        "        GGG     \n" ;

        _defaultCube.prettyPrint();
        assertEquals(expectOutput, _outText.toString());
    }

    //structured basis
    @Test
    public void Should_display_expected_non_default_cube() {
        String expectOutput =
                        "        YWY     \n" +
                        "        GBO     \n" +
                        "        BYW     \n" +
                        "OBO BRW RRG RBG \n" +
                        "WRR BYO WOO YWG \n" +
                        "BOR YYO BRO WGR \n" +
                        "        WWG     \n" +
                        "        BGY     \n" +
                        "        GGY     \n" ;

        _cube.prettyPrint();
        assertEquals(expectOutput, _outText.toString());
    }


    //validateCubeLayout


    //structured basis 1st branch
    //bounds test
    @Test(expected = RuntimeException.class)
    public void Should_fail_with_fewer_number_of_sides() {
        _cubeExposer.validateCubeLayoutExposed(new ArrayList<>());
        assertEquals("Wrong number of sides provided", _errText.toString());
    }

    //bounds test for 1st branch
    @Test(expected = RuntimeException.class)
    public void Should_fail_with_greater_number_of_sides() {
        Colors[][] colors = new Colors[1][1];
        List<Colors[][]> list = new ArrayList<>();

        for(int i = 0; i <= Constants.DIMENSIONS; i++) {
            list.add(colors);
        }

        _cubeExposer.validateCubeLayoutExposed(list);
        assertEquals("Wrong number of sides provided", _errText.toString());
    }

    //structured basis 2nd branch
    //bounds test
    @Test(expected = RuntimeException.class)
    public void Should_fail_with_wrong_center_colors() {
        Colors[][] topFaceColors = {
                {Colors.YELLOW, Colors.WHITE, Colors.YELLOW},
                {Colors.GREEN, Colors.BLUE, Colors.ORANGE},
                {Colors.BLUE, Colors.YELLOW, Colors.WHITE}
        };

        Colors[][] bottomFaceColors = {
                {Colors.WHITE, Colors.WHITE, Colors.GREEN},
                {Colors.BLUE, Colors.BLUE, Colors.YELLOW},
                {Colors.GREEN, Colors.GREEN, Colors.YELLOW}
        };

        Colors[][] frontFaceColors = {
                {Colors.RED, Colors.RED, Colors.GREEN},
                {Colors.WHITE, Colors.ORANGE, Colors.ORANGE},
                {Colors.BLUE, Colors.RED, Colors.ORANGE}
        };

        Colors[][] backFaceColors = {
                {Colors.ORANGE, Colors.BLUE, Colors.ORANGE},
                {Colors.WHITE, Colors.RED, Colors.RED},
                {Colors.BLUE, Colors.ORANGE, Colors.RED}
        };

        Colors[][] leftFaceColors = {
                {Colors.BLUE, Colors.RED, Colors.WHITE},
                {Colors.BLUE, Colors.YELLOW, Colors.ORANGE},
                {Colors.YELLOW, Colors.YELLOW, Colors.ORANGE}
        };

        Colors[][] rightFaceColors = {
                {Colors.RED, Colors.BLUE, Colors.GREEN},
                {Colors.YELLOW, Colors.WHITE, Colors.GREEN},
                {Colors.WHITE, Colors.GREEN, Colors.RED}
        };

        List<Colors[][]> cubeColors = Arrays.asList(topFaceColors, bottomFaceColors, frontFaceColors, backFaceColors, leftFaceColors, rightFaceColors);

        _cubeExposer.validateCubeLayoutExposed(cubeColors);
        assertEquals("Center pieces have repeats", _errText.toString());
    }

    //structured basis 3rd branch
    @Test(expected = RuntimeException.class)
    public void Should_fail_with_wrong_number_of_squares() {
        Colors[][] topFaceColors = {
                {Colors.YELLOW, Colors.WHITE, Colors.YELLOW},
                {Colors.GREEN, Colors.BLUE, Colors.ORANGE},
                {Colors.BLUE, Colors.YELLOW, Colors.WHITE}
        };

        Colors[][] bottomFaceColors = {
                {Colors.WHITE, Colors.WHITE, Colors.GREEN},
                {Colors.BLUE, Colors.WHITE, Colors.YELLOW},
                {Colors.GREEN, Colors.GREEN, Colors.YELLOW}
        };

        Colors[][] frontFaceColors = {
                {Colors.RED, Colors.RED, Colors.GREEN},
                {Colors.WHITE, Colors.ORANGE, Colors.ORANGE},
                {Colors.BLUE, Colors.RED, Colors.ORANGE}
        };

        Colors[][] backFaceColors = {
                {Colors.ORANGE, Colors.BLUE, Colors.ORANGE},
                {Colors.WHITE, Colors.RED, Colors.RED},
                {Colors.BLUE, Colors.ORANGE, Colors.RED}
        };

        Colors[][] leftFaceColors = {
                {Colors.BLUE, Colors.RED, Colors.WHITE},
                {Colors.BLUE, Colors.YELLOW, Colors.ORANGE},
                {Colors.YELLOW, Colors.YELLOW, Colors.ORANGE}
        };

        Colors[][] rightFaceColors = {
                {Colors.RED, Colors.BLUE, Colors.GREEN},
                {Colors.YELLOW, Colors.WHITE, Colors.GREEN},
                {Colors.WHITE, Colors.GREEN, Colors.RED}
        };

        List<Colors[][]> cubeColors = Arrays.asList(topFaceColors, bottomFaceColors, frontFaceColors, backFaceColors, leftFaceColors, rightFaceColors);

        _cubeExposer.validateCubeLayoutExposed(cubeColors);
        assertEquals("Wrong number of each square color present", _errText.toString());
    }

    //structured basis 4th branch
    @Test(expected = RuntimeException.class)
    public void Should_fail_with_wrong_dimensions() {
        Colors[][] topFaceColors = {
                {Colors.YELLOW, Colors.WHITE, Colors.YELLOW},
                {Colors.GREEN, Colors.BLUE, Colors.ORANGE},
                {Colors.BLUE, Colors.YELLOW, Colors.WHITE}
        };

        Colors[][] bottomFaceColors = {
                {Colors.WHITE, Colors.WHITE, Colors.GREEN, Colors.BLUE},
                {Colors.BLUE, Colors.YELLOW},
                {Colors.GREEN, Colors.GREEN, Colors.YELLOW}
        };

        Colors[][] frontFaceColors = {
                {Colors.RED, Colors.RED, Colors.GREEN},
                {Colors.WHITE, Colors.ORANGE, Colors.ORANGE},
                {Colors.BLUE, Colors.RED, Colors.ORANGE}
        };

        Colors[][] backFaceColors = {
                {Colors.ORANGE, Colors.BLUE, Colors.ORANGE},
                {Colors.WHITE, Colors.RED, Colors.RED},
                {Colors.BLUE, Colors.ORANGE, Colors.RED}
        };

        Colors[][] leftFaceColors = {
                {Colors.BLUE, Colors.RED, Colors.WHITE},
                {Colors.BLUE, Colors.YELLOW, Colors.ORANGE},
                {Colors.YELLOW, Colors.YELLOW, Colors.ORANGE}
        };

        Colors[][] rightFaceColors = {
                {Colors.RED, Colors.BLUE, Colors.GREEN},
                {Colors.YELLOW, Colors.WHITE, Colors.GREEN},
                {Colors.WHITE, Colors.GREEN, Colors.RED}
        };

        List<Colors[][]> cubeColors = Arrays.asList(topFaceColors, bottomFaceColors, frontFaceColors, backFaceColors, leftFaceColors, rightFaceColors);

        _cubeExposer.validateCubeLayoutExposed(cubeColors);
        assertEquals("Bad dimensions for input", _errText.toString());
    }

    //bad data
    @Test(expected = NullPointerException.class)
    public void Should_error_if_list_is_null() {
        _cubeExposer.validateCubeLayoutExposed(null);
    }


    //for loops relating to the faces of the cube will never not run at least 1 time,
    //so no branch tests need to be made for those not running


    //areCentersUnique


    //structured basis
    //enters if
    @Test
    public void Should_return_false_if_duplicate_detected() {
        Colors[][] topFaceColors = {
                {Colors.YELLOW, Colors.WHITE, Colors.YELLOW},
                {Colors.GREEN, Colors.BLUE, Colors.ORANGE},
                {Colors.BLUE, Colors.YELLOW, Colors.WHITE}
        };

        Colors[][] bottomFaceColors = {
                {Colors.WHITE, Colors.WHITE, Colors.GREEN},
                {Colors.BLUE, Colors.BLUE, Colors.YELLOW},
                {Colors.GREEN, Colors.GREEN, Colors.YELLOW}
        };

        Colors[][] frontFaceColors = {
                {Colors.RED, Colors.RED, Colors.GREEN},
                {Colors.WHITE, Colors.ORANGE, Colors.ORANGE},
                {Colors.BLUE, Colors.RED, Colors.ORANGE}
        };

        Colors[][] backFaceColors = {
                {Colors.ORANGE, Colors.BLUE, Colors.ORANGE},
                {Colors.WHITE, Colors.RED, Colors.RED},
                {Colors.BLUE, Colors.ORANGE, Colors.RED}
        };

        Colors[][] leftFaceColors = {
                {Colors.BLUE, Colors.RED, Colors.WHITE},
                {Colors.BLUE, Colors.YELLOW, Colors.ORANGE},
                {Colors.YELLOW, Colors.YELLOW, Colors.ORANGE}
        };

        Colors[][] rightFaceColors = {
                {Colors.RED, Colors.BLUE, Colors.GREEN},
                {Colors.YELLOW, Colors.WHITE, Colors.GREEN},
                {Colors.WHITE, Colors.GREEN, Colors.RED}
        };

        List<Colors[][]> cubeColors = Arrays.asList(topFaceColors, bottomFaceColors, frontFaceColors, backFaceColors, leftFaceColors, rightFaceColors);

        assertFalse(_cubeExposer.areCentersUniqueExposed(cubeColors));
    }

    //structured basis
    //does not enter if
    @Test
    public void Should_return_true_if_no_duplicate_detected() {
        assertTrue(_cubeExposer.areCentersUniqueExposed(_cubeColors));
    }


    //hasCorrectColorCounts


    //structured basis
    //enters if
    @Test
    public void Should_return_false_if_wrong_counts_detected() {
        Colors[][] topFaceColors = {
                {Colors.YELLOW, Colors.WHITE, Colors.YELLOW},
                {Colors.GREEN, Colors.BLUE, Colors.YELLOW},
                {Colors.BLUE, Colors.YELLOW, Colors.WHITE}
        };

        Colors[][] bottomFaceColors = {
                {Colors.WHITE, Colors.WHITE, Colors.GREEN},
                {Colors.BLUE, Colors.GREEN, Colors.YELLOW},
                {Colors.GREEN, Colors.GREEN, Colors.YELLOW}
        };

        Colors[][] frontFaceColors = {
                {Colors.RED, Colors.RED, Colors.GREEN},
                {Colors.WHITE, Colors.ORANGE, Colors.ORANGE},
                {Colors.BLUE, Colors.RED, Colors.ORANGE}
        };

        Colors[][] backFaceColors = {
                {Colors.ORANGE, Colors.BLUE, Colors.ORANGE},
                {Colors.WHITE, Colors.RED, Colors.RED},
                {Colors.BLUE, Colors.ORANGE, Colors.RED}
        };

        Colors[][] leftFaceColors = {
                {Colors.BLUE, Colors.RED, Colors.WHITE},
                {Colors.BLUE, Colors.YELLOW, Colors.ORANGE},
                {Colors.YELLOW, Colors.YELLOW, Colors.ORANGE}
        };

        Colors[][] rightFaceColors = {
                {Colors.RED, Colors.BLUE, Colors.GREEN},
                {Colors.YELLOW, Colors.WHITE, Colors.GREEN},
                {Colors.WHITE, Colors.GREEN, Colors.RED}
        };

        List<Colors[][]> cubeColors = Arrays.asList(topFaceColors, bottomFaceColors, frontFaceColors, backFaceColors, leftFaceColors, rightFaceColors);

        assertFalse(_cubeExposer.hasCorrectColorCountsExposed(cubeColors));
    }

    //structured basis
    //does not enter if
    @Test
    public void Should_return_true_if_no_counts_issue_detected() {
        assertTrue(_cubeExposer.hasCorrectColorCountsExposed(_cubeColors));
    }


    //hasCorrectDimensions


    //structured basis
    //enters if
    @Test
    public void Should_return_false_if_wrong_dimensions_detected() {
        Colors[][] topFaceColors = {
                {Colors.YELLOW, Colors.WHITE, Colors.YELLOW, Colors.GREEN},
                {Colors.BLUE, Colors.ORANGE},
                {Colors.BLUE, Colors.YELLOW, Colors.WHITE}
        };

        Colors[][] bottomFaceColors = {
                {Colors.WHITE, Colors.WHITE, Colors.GREEN},
                {Colors.BLUE, Colors.GREEN, Colors.YELLOW},
                {Colors.GREEN, Colors.GREEN, Colors.YELLOW}
        };

        Colors[][] frontFaceColors = {
                {Colors.RED, Colors.RED, Colors.GREEN},
                {Colors.WHITE, Colors.ORANGE, Colors.ORANGE},
                {Colors.BLUE, Colors.RED, Colors.ORANGE}
        };

        Colors[][] backFaceColors = {
                {Colors.ORANGE, Colors.BLUE, Colors.ORANGE},
                {Colors.WHITE, Colors.RED, Colors.RED},
                {Colors.BLUE, Colors.ORANGE, Colors.RED}
        };

        Colors[][] leftFaceColors = {
                {Colors.BLUE, Colors.RED, Colors.WHITE},
                {Colors.BLUE, Colors.YELLOW, Colors.ORANGE},
                {Colors.YELLOW, Colors.YELLOW, Colors.ORANGE}
        };

        Colors[][] rightFaceColors = {
                {Colors.RED, Colors.BLUE, Colors.GREEN},
                {Colors.YELLOW, Colors.WHITE, Colors.GREEN},
                {Colors.WHITE, Colors.GREEN, Colors.RED}
        };

        List<Colors[][]> cubeColors = Arrays.asList(topFaceColors, bottomFaceColors, frontFaceColors, backFaceColors, leftFaceColors, rightFaceColors);

        assertFalse(_cubeExposer.hasCorrectDimensionsExposed(cubeColors));
    }

    //structured basis
    //does not enter if
    @Test
    public void Should_return_true_if_no_dimension_issue_detected() {
        assertTrue(_cubeExposer.hasCorrectDimensionsExposed(_cubeColors));
    }


    //outputSides


    //structured basis
    //full branch coverage
    @Test
    public void Should_output_with_given_layout() {
        List<List<Side>> printLayout = new ArrayList<>();

        printLayout.add(Arrays.asList(null, Side.FRONT, null, null));
        printLayout.add(Arrays.asList(Side.LEFT, Side.RIGHT, Side.RIGHT, Side.BACK));
        printLayout.add(Arrays.asList(null, null, null, null));


        String expectedOutput = "    OOO         \n" +
                                "    OOO         \n" +
                                "    OOO         \n" +
                                "YYY WWW WWW RRR \n" +
                                "YYY WWW WWW RRR \n" +
                                "YYY WWW WWW RRR \n" +
                                "                \n" +
                                "                \n" +
                                "                \n";

        _defaultCubeExposer.outputSidesExposed(printLayout);
        assertEquals(expectedOutput, _outText.toString());
    }

    //bad data
    //too few columns
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void Should_error_if_not_enough_columns_on_the_layout_are_provided() {
        List<List<Side>> printLayout = new ArrayList<>();

        printLayout.add(Arrays.asList(null, Side.FRONT, null));
        printLayout.add(Arrays.asList(Side.LEFT, Side.RIGHT, Side.BACK));
        printLayout.add(Arrays.asList(null, null, null));

        _defaultCubeExposer.outputSidesExposed(printLayout);
    }

    //bad data
    //too few rows
    @Test(expected = IndexOutOfBoundsException.class)
    public void Should_error_if_not_enough_rows_on_the_layout_are_provided() {
        List<List<Side>> printLayout = new ArrayList<>();

        printLayout.add(Arrays.asList(null, null, Side.FRONT, null));
        printLayout.add(Arrays.asList(Side.LEFT, null, Side.RIGHT, Side.BACK));

        _defaultCubeExposer.outputSidesExposed(printLayout);
    }

    //bad data
    //null layout
    @Test(expected = NullPointerException.class)
    public void Should_throw_error_with_null_list() {
        _defaultCubeExposer.outputSidesExposed(null);
    }


    //printBlankLine


    //structure basis, full coverage
    @Test
    public void Should_print_chars_for_a_blank_line() {
        _defaultCubeExposer.printBlankLineExposed();
        assertEquals("    ", _outText.toString());
    }


    //printFaceLine


    //structured basis, full coverage
    @Test
    public void Should_print_the_given_row_of_a_side() {
        _cubeExposer.printFaceLineExposed(Side.BOTTOM, 1);
        assertEquals("BGY ", _outText.toString());
    }

    //bad data
    @Test (expected = NullPointerException.class)
    public void Should_error_if_side_is_null() {
        _cubeExposer.printFaceLineExposed(null, 1);
    }

    //bad data
    //invalid row number
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void Should_error_if_bad_bounds_are_given() {
        _cubeExposer.printFaceLineExposed(Side.BOTTOM, Constants.DIMENSIONS);
    }


    //rotate


    //structure basis
    //basic case
    @Test
    public void Should_rotate_a_default_cube_face_clockwise() {
        String expectOutput =
                        "        BBB     \n" +
                        "        BBB     \n" +
                        "        YYY     \n" +
                        "RRR YYG OOO BWW \n" +
                        "RRR YYG OOO BWW \n" +
                        "RRR YYG OOO BWW \n" +
                        "        WWW     \n" +
                        "        GGG     \n" +
                        "        GGG     \n" ;

        Cube testCube = new Cube();

        testCube.rotate(Side.FRONT, Direction.CLOCKWISE);

        testCube.prettyPrint();
        assertEquals(expectOutput, _outText.toString());

    }

    //structured basis
    //other direction basic case
    @Test
    public void Should_rotate_a_default_cube_face_counter_clockwise() {
        String expectOutput =
                        "        BBB     \n" +
                        "        BBB     \n" +
                        "        WWW     \n" +
                        "RRR YYB OOO GWW \n" +
                        "RRR YYB OOO GWW \n" +
                        "RRR YYB OOO GWW \n" +
                        "        YYY     \n" +
                        "        GGG     \n" +
                        "        GGG     \n" ;

        Cube testCube = new Cube();

        testCube.rotate(Side.FRONT, Direction.COUNTER_CLOCKWISE);

        testCube.prettyPrint();
        assertEquals(expectOutput, _outText.toString());

    }

    //structure basis
    //basic case
    @Test
    public void Should_rotate_any_default_cube_face_clockwise() {
        String expectOutput =
                        "        RBB     \n" +
                        "        RBB     \n" +
                        "        RBB     \n" +
                        "RRG YYY BOO WWW \n" +
                        "RRG YYY BOO WWW \n" +
                        "RRG YYY BOO WWW \n" +
                        "        OGG     \n" +
                        "        OGG     \n" +
                        "        OGG     \n" ;

        Cube testCube = new Cube();

        testCube.rotate(Side.LEFT, Direction.CLOCKWISE);

        testCube.prettyPrint();
        assertEquals(expectOutput, _outText.toString());

    }

    //structure basis
    //advanced case
    @Test
    public void Should_rotate_cube_face_clockwise() {
        String expectOutput =
                "        YWY     \n" +
                        "        GBO     \n" +
                        "        BYW     \n" +
                        "OBO BRW RRG RBG \n" +
                        "WRR BYO WOO YWG \n" +
                        "WGR BOR YYO BRO \n" +
                        "        GBW     \n" +
                        "        GGW     \n" +
                        "        YYG     \n" ;

        Cube testCube = new Cube(_cubeColors);

        testCube.rotate(Side.BOTTOM, Direction.CLOCKWISE);

        testCube.prettyPrint();
        assertEquals(expectOutput, _outText.toString());

    }


    //rotateClockwise tests with rotate and rotateFace/adjustNeighbors


    //rotateFace


    //structured basis, full coverage
    @Test
    public void Should_rotate_face_clockwise(){
        _cubeExposer.rotateFaceExposed(Side.FRONT);

        Colors[][] expectedColors = {
                {Colors.BLUE, Colors.WHITE, Colors.RED},
                {Colors.RED, Colors.ORANGE, Colors.RED},
                {Colors.ORANGE, Colors.ORANGE, Colors.GREEN}
        };

        assertArrayEquals(expectedColors, _cubeExposer.getFace(Side.FRONT).getFullFace());
    }

    //bad data
    @Test(expected = NullPointerException.class)
    public void Should_error_if_side_is_null_in_rotate_face() {
        _cubeExposer.rotateFaceExposed(null);
    }


    //reverseColors


    //structure basis, full coverage
    @Test
    public void Should_reverse_colors_array() {
        Colors[] original = {Colors.RED, Colors.GREEN, Colors.YELLOW, Colors.WHITE};
        Colors[] reverse = {Colors.WHITE, Colors.YELLOW, Colors.GREEN, Colors.RED};

        assertArrayEquals(reverse, _defaultCubeExposer.reverseColorsExposed(original));
    }


    //bad data tests aren't needed behind the barricade, so I will quit testing for bad data


    //adjustNeighbors


    //structured basis, full coverage
    @Test
    public void Should_adjust_neighbors_properly() {
        Cube cube = new Cube(_cubeColors);
        Cube.Exposer exposer = cube. new Exposer();

        String expectOutput =
                        "        YWY     \n" +
                        "        GBO     \n" +
                        "        BYW     \n" +
                        "OBO BRW RRG RBG \n" +
                        "WRR BYO WOO YWG \n" +
                        "WGR BOR YYO BRO \n" +
                        "        WWG     \n" +
                        "        BGY     \n" +
                        "        GGY     \n" ;

        exposer.adjustNeighborsExposed(Side.BOTTOM);
        cube.prettyPrint();


        assertEquals(expectOutput, _outText.toString());
    }


    //colorsFromNeighbor


    //branch 1
    @Test
    public void Should_get_colors_from_neighbors_on_top() {
        Colors[] expectedColors = {Colors.BLUE, Colors.BLUE, Colors.BLUE};

        Face face = _defaultCubeExposer.getFace(Side.TOP);

        Colors[] actualColors = _defaultCubeExposer.colorsFromNeighborExposed(Direction.TOP, face);

        assertArrayEquals(expectedColors, actualColors);
    }

    //branch 2
    @Test
    public void Should_get_colors_from_neighbors_on_right() {
        Colors[] expectedColors = {Colors.GREEN, Colors.ORANGE, Colors.ORANGE};

        Face face = _cubeExposer.getFace(Side.FRONT);

        Colors[] actualColors = _cubeExposer.colorsFromNeighborExposed(Direction.RIGHT, face);

        assertArrayEquals(expectedColors, actualColors);
    }

    //branch 3
    @Test
    public void Should_get_colors_from_neighbors_on_bottom() {
        Colors[] expectedColors = {Colors.BLUE, Colors.RED, Colors.ORANGE};

        Face face = _cubeExposer.getFace(Side.FRONT);

        Colors[] actualColors = _cubeExposer.colorsFromNeighborExposed(Direction.BOTTOM, face);

        assertArrayEquals(expectedColors, actualColors);
    }

    //branch 4
    @Test
    public void Should_get_colors_from_neighbors_on_left() {
        Colors[] expectedColors = {Colors.RED, Colors.WHITE, Colors.BLUE};

        Face face = _cubeExposer.getFace(Side.FRONT);

        Colors[] actualColors = _cubeExposer.colorsFromNeighborExposed(Direction.LEFT, face);

        assertArrayEquals(expectedColors, actualColors);
    }

    //default branch
    @Test(expected = RuntimeException.class)
    public void Should_error_when_getting_a_bad_direction() {
        _defaultCubeExposer.colorsFromNeighborExposed(Direction.COUNTER_CLOCKWISE, _defaultCubeExposer.getFace(Side.TOP));

        assertEquals("Neighbor mappings contain bad directions", _errText.toString());
    }


    //setNeighborsColors


    //branch 1
    @Test
    public void Should_set_colors_for_neighbor_on_top() {
        Cube cube = new Cube();
        Cube.Exposer exposer = cube.new Exposer();
        Face face = exposer.getFace(Side.FRONT);

        Colors[] colorsToSet = {Colors.RED, Colors.YELLOW, Colors.BLUE};

        exposer.setNeighborsColorsExposed(Direction.TOP, face, colorsToSet);

        assertArrayEquals(colorsToSet, face.getTop());
    }

    //branch 2
    @Test
    public void Should_set_colors_for_neighbor_on_right() {
        Cube cube = new Cube();
        Cube.Exposer exposer = cube.new Exposer();
        Face face = exposer.getFace(Side.RIGHT);

        Colors[] colorsToSet = {Colors.RED, Colors.YELLOW, Colors.BLUE};

        exposer.setNeighborsColorsExposed(Direction.RIGHT, face, colorsToSet);

        assertArrayEquals(colorsToSet, face.getRight());
    }

    //branch 3
    @Test
    public void Should_set_colors_for_neighbor_on_bottom() {
        Cube cube = new Cube();
        Cube.Exposer exposer = cube.new Exposer();
        Face face = exposer.getFace(Side.BOTTOM);

        Colors[] colorsToSet = {Colors.RED, Colors.YELLOW, Colors.BLUE};

        exposer.setNeighborsColorsExposed(Direction.BOTTOM, face, colorsToSet);

        assertArrayEquals(colorsToSet, face.getBottom());
    }

    //branch 4
    @Test
    public void Should_set_colors_for_neighbor_on_left() {
        Cube cube = new Cube();
        Cube.Exposer exposer = cube.new Exposer();
        Face face = exposer.getFace(Side.LEFT);

        Colors[] colorsToSet = {Colors.RED, Colors.YELLOW, Colors.BLUE};

        exposer.setNeighborsColorsExposed(Direction.LEFT, face, colorsToSet);

        assertArrayEquals(colorsToSet, face.getLeft());
    }

    //default branch
    @Test(expected = RuntimeException.class)
    public void Should_error_when_setting_a_bad_direction() {
        _defaultCubeExposer.setNeighborsColorsExposed(Direction.COUNTER_CLOCKWISE, _defaultCubeExposer.getFace(Side.FRONT), new Colors[Constants.DIMENSIONS]);

        assertEquals("Neighbor mappings contain bad directions", _errText.toString());
    }
}
