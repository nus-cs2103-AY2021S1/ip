package duke.task;

public class Task {
    /** description of the task. */
    protected String description;
    /** completion status of the task. */
    protected boolean isDone;

    public Task() {
        this.description = "";
        this.isDone = false;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return the description of the task.
     */
    public String getDescription() {
        return this.description;
    }
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns icons representing the completion status of the task.
     * @return icons representing the completion status of the task
     */
    public String getStatusIcon() {
        return (isDone ? "1" : "0");
    }

    /**
     * Sets the isDone status of the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a plainText formatted string representation of the task, for saving and loading into a text file.
     *
     * @return a plainText formatted string representation of the task.
     */
    public String getPlainText() {
        char className = this.getClass().getSimpleName().charAt(0);
        String completionStatus = isDone? "1" : "0";
        return className + " | " + completionStatus + " | " + description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}