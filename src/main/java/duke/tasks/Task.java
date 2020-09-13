package duke.tasks;

/**
 * Task which Duke keeps track of
 */
public class Task {

    protected String description;
    protected String typeOfTask;
    protected boolean isDone;

    /**
     * Constructor for Task class
     * 
     * @param description description of the task
     * @param typeOfTask type of task
     * @param isDone indicates whether the task has been completed or not
     */
    public Task (String description, String typeOfTask, boolean isDone) {
        this.description = description;
        this.typeOfTask = typeOfTask;
        this.isDone = isDone;
    }

    /**
     * Sets the boolean isDone
     * 
     * @param isDone indicates whether the task has been completed or not
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns time of the task for deadline and event tasks
     * 
     * @return time of deadlines or events. Empty string for todos
     */
    public String getTime() {
        return "";
    }

    /**
     * Returns whether the task has been completed or not
     * 
     * @return whether the task has been completed or not
     */
    public Boolean getDone() {
        return this.isDone;
    }

    /**
     * Returns the description of the task
     * 
     * @return description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the type of task
     * 
     * @return type of task
     */
    public String getTypeOfTask() {
        return this.typeOfTask;
    }

    @Override
    public String toString() {
        if (isDone) {
            return String.format ("[\u2713] %s", this.description);
        } else {
            return String.format ("[\u2717] %s", this.description);
        }
    }
}
