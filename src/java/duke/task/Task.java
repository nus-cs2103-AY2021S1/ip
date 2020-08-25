package duke.task;

public abstract class Task {
    protected final String description;
    protected boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone() {
        this.isDone = true;
    }

    public String getStatus() {
        return isDone ? "[\u2713] " : "[\u2718] ";
    }

    /**
     * Returns string representation of task to be written to a file.
     *
     * @return String representation of the Task.
     */
    public abstract String print();

    @Override
    public String toString() {
        return getStatus() + description;
    }
}
