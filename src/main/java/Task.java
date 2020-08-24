public abstract class Task {
    protected final char type;
    protected final String description;
    private boolean isDone;

    Task(char type, String description) {
        this.type = type;
        this.description = description;
        isDone = false;
    }
    Task(char type, String description, boolean isDone) {
        this.type = type;
        this.description = description;
        this.isDone = isDone;
    }
    
    char getType() {
        return type;
    }
    
    String getDescription() {
        return description;
    }
    
    String getStatus() {
        return isDone ? "1" : "0";
    }

    String getStatusIcon() {
        return isDone ? "\u2713" : "\u2718";
    }

    void markAsDone() {
        isDone = true;
    }
    
    abstract String getAt();
    
    abstract String getBy();
}