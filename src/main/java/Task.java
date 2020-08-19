public class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone= false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public Task markDone() {
        this.isDone = true;
        return this;
    }

    public String toString() {
        return "[" + this.getStatusIcon() +"] " + this.name;
    }
}
