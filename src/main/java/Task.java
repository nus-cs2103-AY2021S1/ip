/**
 * Encapsulates details of a Task
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Instantiates a task with a description of it.
     * @param description an explanation of what the task is about
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Visualises the status of a task.
     * @return whether or not a task is done
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }
}
