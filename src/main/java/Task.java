/**
 * Represents a task within <i>Duke</i>.
 */
public class Task {
    /** Name of the {@code Task}. */
    private final String name;
    /** Whether the {@code Task} has been completed. */
    private final boolean isDone;

    // Strings
    /** Check symbol. */
    private static final char CHECK_SYMBOL = '\u2713';
    /** Cross symbol. */
    private static final char CROSS_SYMBOL = '\u2718';

    /**
     * Constructs a new uncompleted {@code Task} object.
     *
     * @param name the name of the {@code Task}.
     */
    public Task(String name) {
        this(name, false);
    }

    /**
     * Constructs a new {@code Task} object.
     *
     * @param name the name of the {@code Task}.
     */
    private Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Marks the {@code Task} as done.
     *
     * @return a new completed {@code Task} object with the same {@code name} as this {@code Task}.
     */
    public Task markAsDone() {
        return new Task(name, true);
    }

    /**
     * Returns the name of this {@code Task} object.
     *
     * @return a string representation of the {@code Task}.
     */
    @Override
    public String toString() {
        char symbol = isDone ? CHECK_SYMBOL : CROSS_SYMBOL;
        return String.format("[%c] %s", symbol, name);
    }
}
