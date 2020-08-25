/**
 * Creates an unspecified Task.
 */
package tasks;

public class Task {
    protected TaskStates state;
    protected String description;

    /**
     * Returns the task created.
     * @param description description of the task.
     */
    Task(String description){
        state = TaskStates.UNDONE;
        this.description = description;
    }

    /**
     * Returns the description of the task.
     * @return String description of task.
     */
    public String getDescription(){
        return description;
    }

    /**
     * Mark the task as Done.
     */
    public void markAsDone(){
        state = TaskStates.DONE;
    }

    /**
     * Checks if a task is done.
     * @return boolean true if task is done.
     */
    public boolean isDone(){
        return state == TaskStates.DONE;
    }

    /**
     * Returns the icon for the status of the task.
     * @return String icon for task status.
     */
    public String getStatusIcon() {
        return (state == TaskStates.DONE ? "\u2713" : "\u2718");
    }

    @Override
    public String toString() {
        switch(state){
            case DONE:
                return "[\u2713] " + description;
            case UNDONE:
                return "[\u2718] " + description;
            default:
                return description;
        }
    }
}
