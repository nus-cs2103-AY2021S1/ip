package duke.utils;

public class Pair<T, U> {
    private T first;
    private U second;

    /**
     * Constructor for Pair.
     * @param first first element
     * @param second second element
     */
    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public U getSecond() {
        return second;
    }

    public void setSecond(U second) {
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }
}
