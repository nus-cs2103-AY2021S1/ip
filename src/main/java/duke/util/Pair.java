package duke.util;

public class Pair<T, U> {

    private T first;
    private U second;

    private Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return this.first;
    }

    public U getSecond() {
        return this.second;
    }

    public static <T, U> Pair<T, U> of(T first, U second) {
        return new Pair<>(first, second);
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", first, second);
    }

}
