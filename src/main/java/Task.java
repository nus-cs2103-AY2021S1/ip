public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) throws PandaBotEmptyTaskDescriptionException {
        this.description = description.strip(); // removes starting and ending white spaces
        if (this.description.length() == 0) {
            throw new PandaBotEmptyTaskDescriptionException(this.getClass().getSimpleName());
        }
        isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    public void markTaskDone() {
        isDone = true;
    }
}
