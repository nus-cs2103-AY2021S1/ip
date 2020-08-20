public class Task {
    String description;
    boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String taskToString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public Task taskDone() {
        this.isDone = true;
        return this;
    }
}