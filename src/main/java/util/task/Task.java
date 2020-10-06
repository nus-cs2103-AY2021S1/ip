package util.task;

/**
 * Parent Class for all Tasks.
 * Task contains a name describing the task and
 * a isDone boolean that indicates whether
 * the task is completed.
 */
public class Task {
    // Variables
    protected String name;
    protected boolean isDone;

    public Task() {

    }
    /**
     * Constructor for Task.
     *
     * @param name  Name of Task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Returns status of the Task.
     *
     * @return Task Status.
     */
    public boolean getStatus() {
        return this.isDone;
    }

    /**
     * Set Task status to specified value.
     *
     * @param status  Status to set Task to.
     */
    public void setStatus(boolean status) {
        this.isDone = status;
    }

    /**
     * Returns name of the Task.
     *
     * @return Lateral location.
     */
    public String getDescription() {
        return this.name;
    }

    /**
     * Set Task description to specified string.
     *
     * @param description  New description for the task.
     */
    public void setDescription(String description) {
        this.name = description;
    }

    /**
     * Returns details of the Task in a format loadable by Duke.
     *
     * @return String in proper format for Duke's save file.
     */
    public String toSaveData() {
        if (isDone) {
            return "1 - " + this.name;
        } else {
            return "0 - " + this.name;
        }
    }

    @Override
    public String toString() {
        // By default print task name and status
        if (isDone) {
            return "[Done] " + this.name;
        } else {
            return "[X] " + this.name;
        }
    }
}
