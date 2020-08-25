package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void completeTask() {
        this.isDone = true;
    }

    public abstract String toSaveFormat();
    
    public boolean contains(String s) {
        return this.description.contains(s);
    }
    
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }
}