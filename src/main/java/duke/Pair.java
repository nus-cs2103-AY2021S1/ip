package duke;

public class Pair<T, U> {
    private final T firstType;
    private final U secondType;

    public Pair(T firstType, U secondType) {
        this.firstType = firstType;
        this.secondType = secondType;
    }

    public T getT() {
        return firstType;
    }

    public U getU() {
        return secondType;
    }
}
