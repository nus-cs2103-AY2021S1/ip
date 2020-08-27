import java.io.Serializable;

public class Task implements Serializable {

    protected String description;
    protected boolean isDone;
    

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task
     * @return returns a tick or a cross, to indicate the status of the task
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Returns the description of the task
     * @return Description of task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a true or false, to indicate if the task has been done
     * @return Whether the task has been done
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Sets the status of a task to "Done"
     */
    public void setDone() {
        this.isDone = true;
    }
}
