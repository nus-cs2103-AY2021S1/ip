/**
 * represents a task
 */

public abstract class Task {
    protected String task;
    protected boolean done;

    public String getStatus() {
        return (done ? "\u2713" : "\u2718");
    }

    public void markAsDone() {
        this.done = true;
    }

    public boolean isDone() {
        return this.done;
    }

    public int isDoneInt() {
        return this.done? 1 : 0;
    }

    public abstract String encode();

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(this.getStatus()).append("] ").append(this.task);
        return sb.toString();
    }
}
