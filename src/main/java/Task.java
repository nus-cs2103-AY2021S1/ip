public class Task {
    protected String description;
    protected boolean isDone;
    protected String data;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public int getDataStatus() {
        return (isDone ? 1 : 0);
    }

    public String getData() {
        return "| " + getDataStatus() + " | " + this.description;
    }
}
