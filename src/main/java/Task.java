public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a special character based on the state of the Task.
     * @return a Tick if the task is marked as done or a cross if not.
     */
    public String getStatusIcon() {
        return isDone? "\u2713" : "\u2718";
    }

    /**
     * Marks the Task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a String to be written into saved data.
     * @return a String that contains the necessary information in the correct format.
     */
    public String storageForm() {
        return "";
    }

    @Override
    public String toString() {
        return "["+getStatusIcon()+"] " + this.description;
    }
}
