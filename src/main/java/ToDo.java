/**
 * Encapsulates the idea of tasks without any date/time attached,
 * hence inherits from Task
 */

public class ToDo extends Task {

    // constants
    private final String LABEL = "T";

    // constructor
    ToDo(boolean isDone, String taskName) {
        super(isDone, taskName);
    }

    /**
     * Returns string representation of a to-do
     * @return string representation of to-do object
     */
    @Override
    public String toString() {
        return "[" + LABEL + "]" + super.toString();
    }
}
