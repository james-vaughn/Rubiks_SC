/*
Constants used for the Rubik's cube project
 */
public final class Constants {

    /*
    Dimension of the rows/cols of a cube
     */
    public static final int DIMENSIONS = 3;

    /*
    Height of the pretty print layout
     */
    public static final int PICTURE_HEIGHT = 3;

    /*
    Width of the pretty print layout
     */
    public static final int PICTURE_WIDTH = 4;


    /*
    Number of sides on a cube;
    assuming a cube is defined as a 3D regular hexahedron
    or, more pedantically, square parallelepiped (with no slant)
    */
    public static final int SIDES_ON_A_CUBE = 6;

    /*
    Number of clockwise rotations (of a face) it takes to equal a counter clockwise turn
    */
    public static final int ROTATIONS_TO_MAKE_A_COUNTER_CLOCKWISE_TURN = 3;
}
