package botbot.tasks;

public abstract class Task {
    protected final char type;
    protected final String description;
    private boolean isDone;

    public Task(char type, String description) {
        this.type = type;
        this.description = description;
        isDone = false;
    }
    public Task(char type, String description, boolean isDone) {
        this.type = type;
        this.description = description;
        this.isDone = isDone;
    }
    
    public char getType() {
        return type;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getStatus() {
        return isDone ? "1" : "0";
    }

    String getStatusIcon() {
        return isDone ? "\u2713" : "\u2718";
    }

    public void markAsDone() {
        isDone = true;
    }
    
    public abstract String getAt();
    
    public abstract String getBy();
}