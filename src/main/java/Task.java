public class Task {
    public String task;
    public boolean done;
    
    protected Task(String task) {
        this.task = task;
        this.done = false;
    }
    
    protected String writeMessage() {
        return "";
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.done = true;
    }

    @Override
    public String toString() {
        return "[" + (this.done ? "✓" : "✗") + "] " + this.task;
    }
}
