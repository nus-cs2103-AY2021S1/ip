package duke.task;

/**
 * Represents a {@link Task} that needs to be done.
 */
public class ToDo extends Task {
    /**
     * Instantiates a ToDo that is by default not completed.
     * @param description The description of the ToDo.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Instantiates a ToDo based on completion Status.
     * @param description The description of the ToDo.
     * @param completionStatus The completion status of the ToDo.
     */
    public ToDo(String description, String completionStatus) {
        super(description, completionStatus);
    }

    /**
     * Overrides getType method in {@link Task}.
     * @return Type of ToDo.
     */
    @Override
    public String getType() {
        return "T";
    }

    /**
     * Returns a String representation of the ToDo.
     * Overrides toString in {@link Task}.
     * @return A String representation of the ToDo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
