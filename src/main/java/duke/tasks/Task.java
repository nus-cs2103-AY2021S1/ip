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
     * Sets the boolean isDone to true
     */
    public void markDone() {
        this.isDone = true;
    }
    public String getTime() {
        return "";
    }
    public Boolean getDone() {
        return this.isDone;
    }
    public String getDescription() {
        return this.description;
    }
    public String getTypeOfTask() {
        return this.typeOfTask;
    }

    @Override
    public String toString() {
        if (isDone) {
            return String.format ("[DONE] %s", this.description);
        } else {
            return String.format ("[NOT DONE] %s", this.description);
        }
    }
}
