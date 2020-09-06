package duke.task;

/**
 * Task class define rules for task object.
 *
 * @author Galvin Leow Wen Yuan
 * @author A0200204J
 * @version v1.0
 */
public class Task {
    private final String description;
    private boolean isDone;

    /**
     * Class constructor.
     * Initialises task class with description and isDone.
     *
     * @param description description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a tick or cross.
     * If isDone is false return cross else tick.
     *
     * @return isDone status in a form of UTF-8 tick or cross.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); // return tick of X symbols
    }

    /**
     * Changes task object isDone to true to indicate task done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns description for task object.
     *
     * @return string description of task object.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns status of task object.
     *
     * @return true/false status of task object.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Checks if two object are equal.
     * If equal true, else false.
     *
     * @param o object to be compare.
     * @return true is same, false if different
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task task = (Task) o;
        return isDone == task.isDone && description.equals(task.description);
    }

    /**
     * Returns summary to the task object.
     *
     * @return string to the task object.
     */
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
