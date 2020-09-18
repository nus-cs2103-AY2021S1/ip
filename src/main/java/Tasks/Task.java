package Tasks;

public class Task {

    private String name;
    private boolean isCompleted;

    /**
     * Constructor for Tasks.Task.
     * @param name
     */
    public Task(String name) {
        this.name = name;
        this.isCompleted = false;
    }

    /**
     * Get symbol represent the state of completion of the task.
     * @return boolean
     */
    public String getStatusIcon() {
        return (isCompleted ? "\u2713" : "\u2718");
    }

    public String getName() {
        return this.name;
    }

    /**
     * Mark the task as completed
     */
    public void markAsDone() {
        this.isCompleted = true;
    }

    /**
     * Get task information
     * @return a string
     */
    public String printTask() {
        return "[" + this.getStatusIcon() + "] " + this.name;
    }

    public int completedAsNumber() {
        return this.isCompleted ? 1 : 0;
    }

    /**
     * Get task information to save
     * @return a string
     */
    public String toSave() {
        return "| " + completedAsNumber() + " | " + this.name;
    }
}
