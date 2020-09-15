import java.io.Serializable;

/**
 * contains necessary information of a task (e.g. whether the task is done)
 */
public class Task implements Serializable {

    protected String description;
    protected boolean isDone;
    /**
     * Constructor of Task
     *
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * getter for the icon status.
     * tick if the task is done; cross if the task is not done
     *
     * @return string format of the icon
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * marks the incomplete tasks as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * getter for the task name
     *
     * @return task name
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * shows the task name, taskIcon
     *
     * @return the string format of the task
     */
    public String toString() {
        return "[" + getStatusIcon() + "]" + getDescription();
    }
}
