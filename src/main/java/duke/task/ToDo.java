package duke.task;

/**
 * Represents tasks without any date/time attached to it.
 */
public class ToDo extends Task {
    /**
     * Constructor
     *
     * @param description Description of the todo.
     * @param isDone Status of the todo - done or not done.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        String box = this.isDone ? "\u2713" : "\u2718";
        return String.format("[T][%s] %s", box, this.description);
    }
}
