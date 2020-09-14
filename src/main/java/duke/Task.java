package duke;

/**
 * Represents a task. A <code>Task</code> object corresponds to
 * a message and a status (whether it is done)
 */

public class Task {
    private String message;
    private boolean isDone;

    Task(String message) {
        this.message = message;
        this.isDone = false;
    }

    Task(String message, boolean isDone) {
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

    public String getPureTypeLetter() {
        //dummy value
        return "";
    }

    public void setDone() {
        this.isDone = true;
    }

    public String getTypeLetter() {
        // dummy value
        return "";
    }

    public String getPrintMessage() {
        return "";
    }

    public String getStoreMessage() {
        return "";
    }

    public String print() {
        return getTypeLetter() + getStatusIcon() + getPrintMessage();
    }
}