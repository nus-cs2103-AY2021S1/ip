package duke.task;

/**
 * Models a todo task.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a String representation of the task for saving.
     *
     * @return String representation of the task for saving purposes.
     */
    @Override
    public String toSaveFormat() {
        return String.format("T | %d | %s", this.isDone ? 1 : 0, this.description);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", getStatusIcon(), this.description);
    }
}
