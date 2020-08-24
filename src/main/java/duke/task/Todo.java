package duke.task;

/**
 * The basic type of tasks.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * Return the string that is intended to be stored in the local database.
     * @return the string to be stored in the local database, the format is
     *         understandable for <code>Storage</code>
     */
    @Override
    public String toDataString() {
        if (super.isDone) {
            return "T | 1 | " + description;
        } else {
            return "T | 0 | " + description + " | ";
        }
    }

    /**
     * Return the string representation of the task.
     * @return the string representation of the task
     */
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + description;
    }
}
