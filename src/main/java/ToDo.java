/**
 * Represents a todo. A todo task only has a description. It does not
 * have a date/time.
 */

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a String representation of the ToDo that will be saved in the
     * hard disk.
     * @return a String representation of the ToDo.
     */
    public String getFormattedString() {
        return "T | " + (super.isDone? 1 : 0) + " | " + super.description;
    }
}
