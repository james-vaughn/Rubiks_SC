import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class StressTest {

    @Test
    public void Should_handle_major_rotations() {
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

        List<Colors[][]> cubeColors = Arrays.asList(topFaceColors, bottomFaceColors, frontFaceColors, backFaceColors, leftFaceColors, rightFaceColors);
        Cube cube = new Cube(cubeColors);

        randomlyRotate(cube);
        cube.prettyPrint();

    }

    private void randomlyRotate(Cube cube) {
        Random rand = new Random();
        Side[] sideValues = Side.values();

        for (int i=0; i<(rand.nextInt(20000)+10000); i++) {

            Side sideToRotate = sideValues[ rand.nextInt(sideValues.length) ];

            cube.rotate(sideToRotate, rand.nextBoolean() ?
                                        Direction.CLOCKWISE :
                                        Direction.COUNTER_CLOCKWISE);
        }
    }
}
