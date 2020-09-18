/**
 * Represents a task.
 * 3 types of tasks: todos, deadline, event
 * use polymorphism to store all tasks in a DS containing Task obj
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Creates a task.
     *
     * @param description task description
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Creates a task.
     *
     * @param description task description
     * @param isDone whether the task is done
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Getter for description.
     *
     * @return task description
     */
    public String getDes() {
        return description;
    }

    /**
     * Shows tick for tasks that are done and cross for undone tasks.
     *
     * @return tick or cross based to status of the task
     */
    public String getStatusIcon() {
        return isDone ? "[\u2713] " : "[\u2718] ";
    }

    /**
     * Marks a task as done.
     */
    public void markDone() {
        isDone = true;
    }

    public String toString() {
        return getStatusIcon() + description;
    }
}
