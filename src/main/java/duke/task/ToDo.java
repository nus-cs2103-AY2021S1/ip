package duke.task;

/**
 * Represents a "to do" task with a description.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    /**
     * Converts the todo into a format that can be easily stored in the data file.
     *
     * @return Formatted todo information.
     */
    @Override
    public String toData() {
        return "T | " + super.toData();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
