public class Task {
    protected String description;
    protected boolean isDone;
    protected final TaskType type;

    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    public String getStatusIcon() {
        return (isDone ? "✓" : "✘"); // return tick or cross symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }
    
    public boolean hasDate() {
        return this.type != TaskType.TODO;
    }

    @Override
    public String toString() {
        return String.format("[%S][%s] %s", type.toString().charAt(0), this.getStatusIcon(), this.description);
    }
}
