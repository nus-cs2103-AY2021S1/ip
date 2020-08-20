public class Task {
    private static final String STRING_FORMAT = "[%s] %s";

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns tick or cross symbols depending on whether the task is done.
     *
     * @return Tick or cross symbol
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    @Override
    public String toString() {
        return String.format(Task.STRING_FORMAT, getStatusIcon(), description);
    }
}
