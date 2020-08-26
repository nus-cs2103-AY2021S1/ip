/**
 * represents a task
 */

public class Task {
    protected String description;
    protected boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public String getStatus() {
        return (done ? "\u2713" : "\u2718");
    }

    public void markAsDone() {
        this.done = true;
    }

    public boolean isDone() {
        return this.done;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(this.getStatus()).append("] ").append(this.description);
        return sb.toString();
    }
}
