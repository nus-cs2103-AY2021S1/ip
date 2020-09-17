package duke;

/**
 * Represents a task. A <code>Task</code> object corresponds to
 * a message and a status (whether it is done)
 */

public abstract class Task {
    private final String message;
    private boolean isDone;

    /** Constructor for task.
     *
     * @param message the message content of task
     */
    Task(String message) {
        this.message = message;
        this.isDone = false;
    }

    /** Constructor for task with status.
     *
     * @param message the message content of task
     * @param isDone the status of task
     */
    Task(String message, boolean isDone) {
        this.message = message;
        this.isDone = isDone;
    }

    /** Returns status of task.
     *
     * @return status boolean
     */
    public boolean getStatus() {
        return isDone;
    }

    /** Returns message of task.
     *
     * @return message string
     */
    public String getMessage() {
        return message;
    }

    /** Returns status icon of task.
     *
     * @return status icon
     */
    public String getStatusIcon() {
        return "[" + (isDone ? "\u2713" : "\u2718") + "] ";
    }

    /** Returns integer representation of status.
     *
     * @return 1 for done, 0 for undone
     */
    public int getStatusNum() {
        return isDone ? 1 : 0;
    }

    /** Returns normal print form of task.
     *
     * @return a string for printing
     */
    public String print() {
        return getTypeLetter() + getStatusIcon() + getPrintMessage();
    }

    /** Sets a task as done. */
    public void setDone() {
        this.isDone = true;
    }

    public abstract String getPureTypeLetter();

    public abstract String getTypeLetter();

    public abstract String getPrintMessage();

    public abstract String getStoreMessage();

}
