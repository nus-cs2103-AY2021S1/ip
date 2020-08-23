/**
 * Represents a todo task to be completed.
 */
public class ToDo extends Task{
    /**
     * Constructor for the todo task.
     * @param details todo details.
     */
    public ToDo(String details) {
        super(details);
    }

    /**
     * Returns a string representation of the todo task.
     * @return String representation.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
