/**
 * Represents a task within <i>Duke</i>.
 */
public class Task {
    /** Name of the {@code Task}. */
    private final String name;

    /**
     * Constructs a new {@code Task} object.
     *
     * @param name The name of the {@code Task}.
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Returns the name of this {@code Task} object.
     *
     * @return a string representation of the {@code Task}.
     */
    @Override
    public String toString() {
        return name;
    }
}
