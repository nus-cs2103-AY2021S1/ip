package duke;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "✓" : "✘");
    }

    public void completeTask() {
        this.isDone = true;
    }
    
    public String getName() {
        return this.description;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
    
    public abstract String encode();
}
