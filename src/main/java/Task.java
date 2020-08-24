public class Task {
    protected boolean isCompleted;
    protected String taskName;

    public Task(String name) {
        this.taskName = name;
        this.isCompleted = false;
    }

    public Task markAsDone(){
        this.isCompleted = true;
        return this;
    }

    public String getStatusIcon() {
        return (isCompleted ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.taskName;
    }
}
