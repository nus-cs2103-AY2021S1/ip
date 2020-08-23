package duke.task;

/**
 * Encapsulates a task that needs to be done.
 */
public class Todo extends Task {
    private static final String identifier = "T";

    /**
     * Initialises a new instance.
     * The newly initialised todo defaults to being incomplete.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Initialises a new instance
     *
     * @param description The description of the todo.
     * @param isDone      Whether the todo is done.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a string representation of the todo.
     * This string representation is prepended by a <code>T</code> to indicate that this task is a
     * Todo item followed by the default string representation of a normal {@link Task}.
     *
     * @return A string representation of the todo.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s", Todo.identifier, super.toString());
    }

    /**
     * Serialises the todo to a string.
     * The serialised todo consists of a <code>T</code>, followed by a <code>|</code>, and
     * then the serialised representation of a normal {@link Task}
     *
     * @return A string representing the serialised todo.
     */
    @Override
    public String serialise() {
        return String.format("%s | %s", Todo.identifier, super.serialise());
    }
}
