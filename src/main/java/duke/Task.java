package duke;

/**
 * Represents a task. A task has a description and a state representing if it is done.
 */
public abstract class Task {
    protected String desc;
    protected boolean isDone;

    public Task(String desc) {
        this.desc = desc;
    }

    public void markDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return String.format("[%c] %s", getDoneIcon(), desc);
    }

    private char getDoneIcon() {
        return isDone ? '\u2713' : '\u2717';
    }

    protected String toSavedStringIsDone() {
        return isDone ? "1" : "0";
    }

    public abstract String toSavedString();

    public abstract boolean contains(String s);
}
