/**
 * Encapsulates the idea of a task
 */
public class Task {

    // constants (ticks and crosses)
    private final String CROSS = "✗";
    private final String CHECK = "✓";

    // instance variables
    private boolean isDone; // state of task
    private String taskName; // name of task

    // constructor
    Task(boolean isDone, String taskName) {
        this.isDone = isDone;
        this.taskName = taskName;
    }

    // methods
    /**
     * Returns string representation of a task
     * @return string representation of task object
     */
    @Override
    public String toString() {
        String marker = isDone ? CHECK : CROSS;
        return "[" + marker + "] " + taskName;
    }

    /**
     * Sets the state of the task to be completed
     */
    public void setTaskAsDone() {
        isDone = true;
    }
}
