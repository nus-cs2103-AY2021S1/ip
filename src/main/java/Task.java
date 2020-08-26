import java.io.Serializable;

/**
 * Represents a serializable task.
 */
public class Task implements Serializable {
    private final String taskDescription;
    private boolean isDone = false;

    /**
     * Constructs a new Task instance containing a task description.
     *
     * @param description The task description.
     */
    Task(String description) {
        taskDescription = description;
    }

    /**
     * Marks the task as "done".
     */
    void markAsDone() {
       this.isDone = true;
       Ui.showMarkedAsDone(this);
    }

    /**
     * Returns a string representation of the task.
     *
     * @return The formatted string.
     */
    public String toString() {
        String status = isDone ? "\u2713" : "\u2718";
        return "[" + status + "] " + taskDescription;
    }
}
