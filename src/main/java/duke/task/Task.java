package duke.task;

/**
 * Abstract class that models a task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns icon representing whether the task is done or not.
     * 
     * @return Icon representing whether the task is done or not.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Sets the task to be done.
     */
    public void completeTask() {
        this.isDone = true;
    }

    /**
     * Returns a String representation of the task for saving.
     * 
     * @return String representation of the task for saving purposes.
     */
    public abstract String toSaveFormat();
    
    public boolean contains(String s) {
        return this.description.contains(s);
    }
    
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }
}