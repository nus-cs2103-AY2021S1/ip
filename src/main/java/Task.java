public class Task {

    private boolean completed;
    public String name;

    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    /**
     * Get symbol represent the state of completion of the task.
     * @return boolean
     */
    public String getStatusIcon() {
        return (completed ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Mark the task as completed
     */
    public void markAsDone() {
        this.completed = true;
    }

    /**
     * Get task information
     * @return a string
     */
    public String printTask() {
        return "[" + this.getStatusIcon() + "] " + this.name;
    }

    public int completedAsNumber() {
        return this.completed ? 1 : 0;
    }

    /**
     * Get task information to save
     * @return a string
     */
    public String toSave() {
        return "| " + completedAsNumber() + " | " + this.name;
    }
}
