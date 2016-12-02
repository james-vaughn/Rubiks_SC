import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class Neighbors {


    public static final Map<Side, Pair<Direction,Side>> NeighborMappings;

    //directions based off of the net in the design doc
    static {
        Map<Side, Pair<Direction,Side>> mappings = new HashMap<>();

        mappings.put(Side.TOP, new Pair<>(Direction.TOP, Side.BACK));
        mappings.put(Side.TOP, new Pair<>(Direction.TOP, Side.LEFT));
        mappings.put(Side.TOP, new Pair<>(Direction.TOP, Side.RIGHT));
        mappings.put(Side.TOP, new Pair<>(Direction.TOP, Side.FRONT));

        mappings.put(Side.FRONT, new Pair<>(Direction.TOP, Side.BOTTOM));
        mappings.put(Side.FRONT, new Pair<>(Direction.RIGHT, Side.LEFT));
        mappings.put(Side.FRONT, new Pair<>(Direction.LEFT, Side.RIGHT));
        mappings.put(Side.FRONT, new Pair<>(Direction.BOTTOM, Side.TOP));

        mappings.put(Side.LEFT, new Pair<>(Direction.LEFT, Side.TOP));
        mappings.put(Side.LEFT, new Pair<>(Direction.RIGHT, Side.BACK));
        mappings.put(Side.LEFT, new Pair<>(Direction.LEFT, Side.FRONT));
        mappings.put(Side.LEFT, new Pair<>(Direction.LEFT, Side.BOTTOM));

        mappings.put(Side.RIGHT, new Pair<>(Direction.RIGHT, Side.TOP));
        mappings.put(Side.RIGHT, new Pair<>(Direction.LEFT, Side.BACK));
        mappings.put(Side.RIGHT, new Pair<>(Direction.RIGHT, Side.FRONT));
        mappings.put(Side.RIGHT, new Pair<>(Direction.RIGHT, Side.BOTTOM));

        mappings.put(Side.BACK, new Pair<>(Direction.TOP, Side.TOP));
        mappings.put(Side.BACK, new Pair<>(Direction.RIGHT, Side.RIGHT));
        mappings.put(Side.BACK, new Pair<>(Direction.LEFT, Side.LEFT));
        mappings.put(Side.BACK, new Pair<>(Direction.BOTTOM, Side.BOTTOM));

        mappings.put(Side.BOTTOM, new Pair<>(Direction.BOTTOM, Side.BACK));
        mappings.put(Side.BOTTOM, new Pair<>(Direction.BOTTOM, Side.RIGHT));
        mappings.put(Side.BOTTOM, new Pair<>(Direction.BOTTOM, Side.LEFT));
        mappings.put(Side.BOTTOM, new Pair<>(Direction.BOTTOM, Side.FRONT));

        NeighborMappings = Collections.unmodifiableMap(mappings);
    }

}