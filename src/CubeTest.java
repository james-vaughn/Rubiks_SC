import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CubeTest {

    private Cube _defaultCube,
                 _cube;

    List<Colors[][]> _cubeColors;

    private Cube.Exposer _cubeExposer;
    private ByteArrayOutputStream outText = new ByteArrayOutputStream();
    private ByteArrayOutputStream errText = new ByteArrayOutputStream();

    @Before
    public void SetUp() {
        System.setOut(new PrintStream(outText));
        System.setErr(new PrintStream(errText));

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
        assertEquals(expectOutput, outText.toString());
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
        assertEquals(expectOutput, outText.toString());
    }


    //rotate


    //r
}
