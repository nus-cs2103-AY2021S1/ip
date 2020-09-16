package duke;

/**
 * Represents a task. A <code>Task</code> object corresponds to
 * a message and a status (whether it is done)
 */

public abstract class Task {
    private final String message;
    private boolean isDone;

    Task(final String message) {
        this.message = message;
        this.isDone = false;
    }

    Task(final String message, final boolean isDone) {
        this.message = message;
        this.isDone = isDone;
    }

    public String getMessage() {
        return message;
    }

    public String getStatusIcon() {
        return "[" + (isDone ? "\u2713" : "\u2718") + "] ";
    }

    public int getStatusNum() {
        return isDone ? 1 : 0;
    }

    public String print() {
        return getTypeLetter() + getStatusIcon() + getPrintMessage();
    }

    public void setDone() {
        this.isDone = true;
    }

    public abstract String getPureTypeLetter();

    public abstract String getTypeLetter();

    public abstract String getPrintMessage();

    public abstract String getStoreMessage();


}