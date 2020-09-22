package duke.common;

public class Pair<T, U> {
    private final T firstType;
    private final U secondType;

    /**
     * A constructor for a Pair class containing two arbitrary objects.
     * @param a the first object in the Pair.
     * @param b the second object in the Pair.
     */
    public Pair(T a, U b) {
        this.firstType = a;
        this.secondType = b;
    }

    public T getT() {
        return firstType;
    }

    public U getU() {
        return secondType;
    }
}
