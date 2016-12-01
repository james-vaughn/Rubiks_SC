import org.junit.Before;
import org.junit.Test;

public class CubeTest {

    Cube _defaultCube,
         _cube;

    @Before
    public void SetUp() {
        _defaultCube = new Cube();
    }


    //prettyPrint
    @Test
    public void Should_display_expected_default_cube() {
        _defaultCube.prettyPrint();
    }
}
