public class Task {
    protected String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public boolean markAsDone() {
        if (this.isDone) {
            return false;
        }
        else {
            this.isDone = true;
            return true;
        }
    }

    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}