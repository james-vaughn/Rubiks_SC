/**
 *Associative, generic pairing between 2 objects
 */
public class Pair<S,T> {

    /**
     * Left/first object
     */
    private S first;

    /**
     *Right/second object
     */
    private T second;

    /**
     *Constructor for a pair object
     *@param first  Left object to store
     *@param second Right object to store
     */
    public Pair(S first, T second) {
        this.first = first;
        this.second = second;
    }

    /**
     *Getter for the left object
     *@return the left/first element
     */
    public S first() {
        return first;
    }

    /**
     *Setter for the left object
     *@param first The new object for the left/first object
     */
    public void first(S first) {
        this.first = first;
    }

    /**
     *Getter for the right object
     *@return the right/second element
     */
    public T second() {
        return second;
    }

    /**
     *Setter for the right object
     *@param second The new object for the right/second object
     */
    public void second(T second) {
        this.second = second;
    }
}
