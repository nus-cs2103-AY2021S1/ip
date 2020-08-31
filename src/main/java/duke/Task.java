package duke;

/**
 * An abstract class to be inherited by more specific type of event
 */
public abstract class Task {
    protected String desc;
    protected boolean isDone;

    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    /**
     * use to mark this task as done
     */
    public void setDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        char sign = (isDone ? '✓' : '✗');
        return String.format("[%c] %s", sign, desc);
    }

    /**
     * abstract method to be overridden by child classes.
     *
     * @return the representation of the event when written to disk
     */
    protected abstract String toDisk();
}