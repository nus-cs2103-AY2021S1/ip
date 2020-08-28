package duke.task;

public abstract class Task {
    protected String task;
    protected boolean done;

    Task(String task) {
        this.task = task;
        this.done = false;
    }
    
    Task(String task, boolean done) {
        this.task = task;
        this.done = done;
    }

    /**
     * Gets the task description.
     * 
     * @return String of task description.
     */
    public String getTaskDescription() {
        return task;
    }
    
    abstract public String toDataString();

    public void setDone() {
        this.done = true;
    }

    public String getStatusToString() {
        return done ? "✓" : "✘";
    }

    @Override
    public String toString() {
        return "[" + getStatusToString() + "] " + task;
    }
}
