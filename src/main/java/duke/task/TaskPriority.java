package duke.task;

public enum TaskPriority {
    NONE("\u24ff"), LOW("\u2778"), MEDIUM("\u2777"), HIGH("\u2776");

    private final String symbol;

    TaskPriority(String symbol) {
        this.symbol = symbol;
    }

    public String toSymbol() {
        return symbol;
    }
}
