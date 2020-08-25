package duke.task;

/**
 * Represents a Task which is a Todo.
 */
public class ToDo extends Task {
    /**
     * Creates a Todo Task.
     *
     * @param description Description of Todo.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the String representation of a Todo.
     *
     * @return String representation of a Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the text representation of a Todo to be saved in a text file.
     *
     * @return Text representation of Todo.
     */
    @Override
    public String toData() {
        String isDone = super.isDone ? "1" : "0";
        String separator = "~";
        return "T" + separator + isDone + separator + super.description + "\n";
    }
}
