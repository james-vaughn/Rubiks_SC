import java.util.*;

/**
Container for the mapping of sides to their neighbors
 */
public final class Neighbors {

    /**
    Map of a side to all of the neighbors of that side;
    The neighbors consist of associated sides and directions from that side to the current side
     */
    public static final Map<Side, List<Pair<Direction,Side>>> NeighborMappings;

    //directions based off of the net in the design doc

    /**
    Essentially, to get the associated directions and side, you must look at the net (or an actual cube)

    The associate sides are the adjacent sides which touch the current face. The only faces which do not
    touch on a cube are the ones opposed with each other; and due to symmetry of a cube,
    technically, the sides which are opposed should just have inverted direction values but the same neighbors.

    To get the direction from a cube is straight forward if you have a rubiks cube. You can just rotate a face
    and see what changes.

    To get the directions from the net is trickier. You look at what the neighboring faces are with this layout:

    				   Top
		Back	Left   Front	Right
					   Bottom

	You can use other layouts, however the directions get inverted and the needed rotations get inverted as well.

	To the directions from this layout, you take the face you are rotating, and find the 4 connected faces.
	Since cubes are 3D, the middle row can slide like so:

	                   Top
		Right   Back   Left   Front
				       Bottom

	This is important for determining what side can touch what other side.

	So the direction to *this* side from your neighbor is the direction. So in the above net,
	if the side is left, then the direction paired with front is left (left is left of front).

	Rotation is key here, as Back has nothing above it now, but if we slide it, Top is above it, so it has top as a side

	Ultimately, we need this info to know what part of what face a certain rotation would affect (used in rotator)
     */
    static {
        Map<Side, List<Pair<Direction,Side>>> mappings = new HashMap<>();

        mappings.put(Side.TOP, Arrays.asList(new Pair<>(Direction.TOP, Side.BACK),
                                             new Pair<>(Direction.TOP, Side.RIGHT),
                                             new Pair<>(Direction.TOP, Side.FRONT),
                                             new Pair<>(Direction.TOP, Side.LEFT)));

        mappings.put(Side.FRONT, Arrays.asList(new Pair<>(Direction.TOP, Side.BOTTOM),
                                               new Pair<>(Direction.RIGHT, Side.LEFT),
                                               new Pair<>(Direction.BOTTOM, Side.TOP),
                                               new Pair<>(Direction.LEFT, Side.RIGHT)));

        mappings.put(Side.LEFT, Arrays.asList(new Pair<>(Direction.LEFT, Side.TOP),
                                              new Pair<>(Direction.LEFT, Side.FRONT),
                                              new Pair<>(Direction.LEFT, Side.BOTTOM),
                                              new Pair<>(Direction.RIGHT, Side.BACK)));

        mappings.put(Side.RIGHT, Arrays.asList(new Pair<>(Direction.RIGHT, Side.TOP),
                                               new Pair<>(Direction.LEFT, Side.BACK),
                                               new Pair<>(Direction.RIGHT, Side.BOTTOM),
                                               new Pair<>(Direction.RIGHT, Side.FRONT)));

        mappings.put(Side.BACK, Arrays.asList(new Pair<>(Direction.TOP, Side.TOP),
                                              new Pair<>(Direction.LEFT, Side.LEFT),
                                              new Pair<>(Direction.BOTTOM, Side.BOTTOM),
                                              new Pair<>(Direction.RIGHT, Side.RIGHT)));

        mappings.put(Side.BOTTOM, Arrays.asList(new Pair<>(Direction.BOTTOM, Side.FRONT),
                                                new Pair<>(Direction.BOTTOM, Side.RIGHT),
                                                new Pair<>(Direction.BOTTOM, Side.BACK),
                                                new Pair<>(Direction.BOTTOM, Side.LEFT)
                                                ));

        NeighborMappings = Collections.unmodifiableMap(mappings);
    }

}