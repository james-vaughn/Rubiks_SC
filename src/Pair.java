public class Pair<S,T> {
    private S first;
    private T second;

    public Pair(S first, T second) {
        this.first = first;
        this.second = second;
    }

    public S first() {
        return first;
    }

    public void first(S first) {
        this.first = first;
    }

    public T second() {
        return second;
    }

    public void second(T second) {
        this.second = second;
    }
}
