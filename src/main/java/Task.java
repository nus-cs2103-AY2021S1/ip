/**
 * Encapsulates the Task object, contains information about the description and whether it is completed. 
 */
public abstract class Task {

    /**
     * Represents the description of the task.
     */
    protected String description;

    /**
     * Represents whether the task is completed.
     */
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a tick or a cross depending on whether the task is done.
     * @return a tick or a cross.
     */
    public String getStatusIcon() {
        return (isDone ? "[\u2713] " : "[\u2718] "); //return tick or X symbols
    }

    public void markAsDone() {
        isDone = true;
    }

    /**
     * Converts to a string format to be saved in a text file.
     *
     * @return a string representation of the task object.
     */
    public String saveAsString() {
        return " | " + (isDone ? "1" : "0") + " | " + description;
    }
    
    @Override
    public String toString() {
        return getStatusIcon() + description;
    }
}