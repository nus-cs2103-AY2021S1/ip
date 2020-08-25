package duke;
/**
 * Is responsible for basic functionality of task
 * They are different types of task
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * constructor of Task
     * @param description description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * get status icon for task for indicating whether task is done or not
     * @return status icon indicating whether task is done or not
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * mark task as done
     */
    public void markAsDone(){
        isDone = true;
    }

    /**
     * represents task in string format
     * @return string representation of task
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
