package duke.task;

/**
 * Abstract class for Task-related classes to inherit from.
 */
public abstract class Task {
    private final String description;
    private boolean isDone;

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * An ID of the task, e.g. "T" for Todo, "D" for Deadline, etc.
     * Used in Json serialization (Storage class).
     * @return String ID of task.
     */
    public abstract String getTaskIdentifier();

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        String checkMark = this.isDone ? "X" : " ";
        return squareBox(this.getTaskIdentifier())
            + squareBox(checkMark)
            + " "
            + this.description;
    }

    private String squareBox(String str) {
        return "[" + str + "]";
    }
}
