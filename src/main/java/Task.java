public class Task {
    protected boolean isDone;
    protected String description;

    public Task(String description) {
        isDone = false;
        this.description = description;
    }

    public Task(boolean isDone, String description) {
        this.isDone = isDone;
        this.description = description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String toStoreFormat(){
        return "";
    }
}
