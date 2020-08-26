package duke.task;

/**
 * Todo is a sub-class of Task. Todo is a Task without time information.
 * Can be used to store daily activity that is not crucial and non-formal
 * but is targetted to be done.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo.
     *
     * @param description Description of the Todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Gets information about this Todo except its status (done or not done).
     *
     * @return a String array that contains symbol type for Todo ("T") and description.
     */
    @Override
    public String[] getInfo() {
        return new String[] {"T", description};
    }

    /**
     * Returns String representation of Todo.
     *
     * @return String that represent this Todo.
     */
    // Return the type icon
    public String toString() {
        return " [T]" + super.toString();
    }
}
