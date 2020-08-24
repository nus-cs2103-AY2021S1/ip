package duke;

/**
 * This is an abstract class that represents a Task with description and a status.
 * The class has common functionality that is inherited.
 */
public abstract class Task {

    protected String description;
    protected boolean isDone;

    /**
     * The constructor for a Task. This is for use by its sub-classes..
     * Hence all subclasses should have description.
     * @param description The string that describes the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * This method checks if the task is done or not.
     * @return UTF-8 encoding of a tick(task is done) or cross(task is not done).
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * This method allows user to mark this task as done
     */
    public void markAsDone() {
        isDone = true;
    }


    public String getDescription() {
        return this.description;
    }

    public boolean checkStatus() {
        return this.isDone;
    }

    /**
     * This abstract method requires all sub classes to implement so that
     * the type of the task can be identified.
     * @return returns string representation of the task type
     */
    public abstract String getTaskType();
}
