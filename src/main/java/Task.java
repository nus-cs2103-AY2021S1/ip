/**
 * Represents a Task, which is an abstract class that can be used to represent
 * different types of tasks.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a Task object with the given description.
     * 
     * @param description the description of the task
     * @throws PandaBotEmptyTaskDescriptionException If the description given is empty
     */
    public Task(String description) throws PandaBotEmptyTaskDescriptionException {
        this.description = description.strip(); // removes starting and ending white spaces
        if (this.description.length() == 0) {
            throw new PandaBotEmptyTaskDescriptionException(this.getClass().getSimpleName());
        }
        isDone = false;
    }

    /**
     * Returns a String that represents a status icon 
     * which marks whether the task is done
     * 
     * @return a String that represents a status icon which marks whether the task is done 
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }
    
    public String getDescription() {
        return description;
    }

    /**
     * Returns a String representation of the task that is displayed to the user
     * 
     * @return a String Representation of the task that is displayed to the user
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    /**
     * Returns a String representation of the task for saving to the save file 
     * 
     * @return a String representation of the task for saving to the save file
     */
    public String saveAsText() {
        return String.format("%d | %s", (isDone ? 1 : 0), description);
    }

    /**
     * Marks the task as done.
     */
    public void markTaskDone() {
        isDone = true;
    }
}
