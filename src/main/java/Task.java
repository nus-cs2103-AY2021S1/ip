// Group each action the user input as a task, with the description and status if the task is done
public class Task {
    private String description;
    private boolean isDone;

    Task(String description) {
        this.description = description;
        isDone = false;
    }

    // Get status icon for the current task, tick = done, cross = no done
    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Update the status of the task
     *
     * @param isDone New status for the task
     */
    public void updateStatus(boolean isDone) {
        this.isDone = isDone;
    }

    // Print the status of the task before the task description
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
