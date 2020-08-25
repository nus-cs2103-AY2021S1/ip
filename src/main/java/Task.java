/**
 * Contains task description and completion status of task.
 **/
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param description Task description
     **/
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Retrieves status and returns icon corresponding to status.
     *
     * @return Icon corresponding to status.
     **/
    public String getStatusIcon() {
        return (isDone ? "✓" : "X"); //return tick or X symbols
    }

    /**
     * Gets task description.
     *
     * @return Task description.
     **/
    public String getDescription() {
        return description;
    }

    /**
     * Marks task as done.
     **/
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Checks if task is done.
     *
     * @return If Task is Done.
     **/
    public boolean checkIfDone() {
        return isDone;
    }

    public String getStoredString(){
        return (isDone? "1 " : "0 ") + description;
    }

    public String toString(){
        return getStatusIcon() + " " + description;
    }
}
