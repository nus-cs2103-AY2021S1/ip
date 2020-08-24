/**
 * Task is the parent class of To-do, Deadline, and Event.
 */
public class Task {

    protected String description;
    protected boolean isDone;
    protected int id;

    /**
     * Constructor initialises a new Task object.
     *
     * @param description describes the task.
     * @param id position of task.
     */
    public Task(String description, int id) {
        this.description = description;
        this.isDone = false;
        this.id = id;
    }

    /**
     * Retrieves the icon for the task.
     *
     * @return icon to determine whether task is completed.
     */
    public String getStatusIcon() {
        return (isDone ? "✓" : "✘");
    }

    /**
     *  Marks a task as complete.
     */
    public void markedDone() {
        this.isDone = true;
    }

}
