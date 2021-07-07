package duke.util;

/**
 * Utility class representing a pair of objects, possibly of different types.
 * @param <T> Type of the first object.
 * @param <U> Type of the second object.
 */
public class Pair<T, U> {

    private T first;
    private U second;

    /** Constructs the pair object from the objects provided */
    private Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Returns the first object.
     *
     * @return the first object in the pair.
     */
    public T getFirst() {
        return this.first;
    }

    /**
     * Returns the second object.
     *
     * @return the second object in the pair.
     */
    public U getSecond() {
        return this.second;
    }

    /**
     * Returns a pair object containing the two objects provided as arguments.
     *
     * @param first first object in the pair.
     * @param second second object in the pair.
     * @param <T> type of the first object.
     * @param <U> type of the second object.
     * @return a pair object containing both objects.
     */
    public static <T, U> Pair<T, U> of(T first, U second) {
        return new Pair<>(first, second);
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", first, second);
    }

}
