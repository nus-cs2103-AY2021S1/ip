
/**
 * <h1> Task Class </h1>
 * This is the parent task class that contains
 * the properties and methods that the various
 * types of child tasks requires.
 *
 * @author Lee Penn Han.
 * @version 1.0.
 * @since 2020-01-25.
 */
public class Task {
    String task;
    boolean done;

    protected Task(String task) {
        this.task = task;
        this.done = false;
    }

    /**
     * Returns the current task as a String.
     *
     * @return the task as a String.
     */
    public String getTask() {
        return this.task;
    }


    /**
     * Returns the status of whether the task is done.
     *
     * @return the status of the task.
     */
    public boolean isDone() {
        return this.done;
    }

    /**
     * Instantiates a Task object.
     *
     * @param task the task to be done from User Input.
     * @return Task Object.
     */
    public static Task setTask(String task) {
        return new Task(task);
    }

    /**
     * Changes the status of done from false to true to
     * indicate that a Task has been completed.
     */
    public void setDone() {
        this.done = true;
    }
}
