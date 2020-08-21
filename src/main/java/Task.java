public class Task {
    private static final String STRING_FORMAT = "[%s] %s";

    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
        //return tick or X symbols
    }

    @Override
    public String toString() {
        return String.format(Task.STRING_FORMAT, getStatusIcon(), description);
    }
}
