package duke.task;

public class Task {
    protected boolean isDone;
    protected String description;

    /**
     * Constructor for a Task.
     * Should only be accessible from the children of Task.
     * @param description the description of the task.
     */
    protected Task(String description) {
        this.isDone = false;
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Returns the type of the task.
     * A task may be a todo, an event, or a description.
     * @return an enum representing the type of the task.
     */
    public TaskType getType() {
        return TaskType.NONE;
    }

    /**
     * Returns the date associated with the task.
     * If there are no dates associated to the task, it returns an empty String.
     * @return a String representing the date associated to the task.
     */
    public String getDate() {
        return "";
    }

    /**
     * Returns the delimiter associated with the task.
     * The delimiter is a String that separates the description from the date
     * If there are no delimiter associated to the task, it returns an empty String.
     * @return a String representing the delimiter associated to the task.
     */
    public String getDelimiter() {
        return "";
    }
}
