/**
 * Represents a todo. A todo is a task that has a description of the activity.
 * It does not have a date or time.
 */

public class ToDo extends Task {

    /**
     * Constructs a ToDo with the specified description.
     * @param description Description of the activity associated with the ToDo.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a representation of the ToDo that will be saved in the hard disk.
     * @return String representation of the ToDo.
     */
    public String getFormattedString() {
        return "T | " + (super.isDone ? 1 : 0) + " | " + super.description;
    }
}
