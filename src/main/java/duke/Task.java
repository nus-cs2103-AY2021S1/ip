package duke;

import java.time.LocalDate;

/**
 * Represents a work that needs to be done.
 * Contains a name of that work, represented by a String.
 * Contains the done status of the work. represented by a boolean.
 */
public abstract class Task implements Cloneable {
    protected String taskName;
    protected boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName.trim();
        this.isDone = false;
    }

    /**
     * Class getter and setter routines.
     */
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() { this.isDone = false; }

    public abstract String getDate();

    public abstract void setDate(LocalDate taskDate);

    /**
     * Visualises the done status of a task with an icon.
     *
     * @return String version of check and cross, whichever needed.
     */
    public String getStatusIcon() {
        return isDone ?  "\u2713" : "\u2718";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), taskName);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
