public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return ("[" + (isDone ? "\u2713" : "\u2718") + "]"); //return tick or X symbols
    }

    public void markDone() {
        this.isDone = true;
    }

    public String toString() {
        return this.getStatusIcon() + description;
    }

    public void print() {
        System.out.println("  " + this.getStatusIcon() + description);
    }


}
