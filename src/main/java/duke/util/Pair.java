package duke.util;

public class Pair<T, U> {

    public T first;
    public U second;

    private Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public static <T, U> Pair<T, U> of(T first, U second) {
        return new Pair<>(first, second);
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", first, second);
    }

}
