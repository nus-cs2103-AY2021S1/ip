/**
 * Encapsulates the idea of deadlines, tasks that need to
 * be done before a specific time. Thus it inherits from Task
 */

public class Deadline extends Task {

    // constants
    private final String LABEL = "D";

    // instance variables
    private String time;

    // constructor
    Deadline(boolean isDone, String taskName, String time) {
        super(isDone, taskName);
        this.time = time;
    }

    /**
     * Returns string representation of a deadline
     * @return string representation of a deadline object
     */
    @Override
    public String toString() {
        return "[" + LABEL + "]"
                + super.toString() + " (by: " + time + ")";
    }
}