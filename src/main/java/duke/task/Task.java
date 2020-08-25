package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public Task markAsDone() {
        isDone = true;
        return this;
    }
    
    public String getStatusIcon() {
        return "[" + (isDone ? "\u2713" : "\u2718") + "]"; //return tick or X symbols
        //return "[" + (isDone ? "Done" : "Not done") + "]";
    }

    public boolean isDone() {
        return this.isDone;    
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public String getTime() {
        return "";
    }
    
    public abstract String getTypeIcon();

    @Override
    public abstract String toString();
}
