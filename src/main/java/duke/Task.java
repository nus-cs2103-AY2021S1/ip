package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * An abstract class to be inherited by more specific type of event
 */
public abstract class Task {
    protected final String desc;
    protected boolean isDone;

    /**
     * Constructor for Task, to be called only by subclasses
     *
     * @param desc description for the task
     */
    protected Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    public static String dateToString(LocalDate date) {
        return DateTimeFormatter.ofPattern("MMM d yyyy").format(date);
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
