import java.util.ArrayList;
import java.util.List;

public final class Cube{

    List<Face> _cubeFaces = new ArrayList<>();

    //Default to a full solved cube
    public Cube() {
        for(Colors faceColor : Colors.values()) {
            _cubeFaces.add(new Face(faceColor));
        }
    }

    //TODO barricade
    public Cube(List<Colors[][]> faceColors) {

    }

    //handles manipulation of the cube
    private static class Rotator {

    }
}
