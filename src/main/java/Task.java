public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[\u2713] " : "[\u2718] "); //return tick or X symbols
    }

    public void markAsDone(){
        isDone = true;
    }

    public String saveAsString() {
        return " | " + (isDone ? "1" : "0") + " | " + description;
    }
    
    @Override
    public String toString() {
        return getStatusIcon() + description;
    }
}