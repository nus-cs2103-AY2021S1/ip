package shiro.task;

import java.time.LocalDate;

/**
 * represents a task
 */
public abstract class Task {
    protected String task;
    protected boolean done;

    protected String getStatus() {
        return (done ? "\u2713" : "\u2718");
    }

    /**
     * marks the task as done
     */
    public void markAsDone() {
        this.done = true;
    }

    /**
     * returns a boolean value indicating if the task has been marked as done
     * @return true if the task has been marked as done and false otherwise
     */
    public boolean isDone() {
        return this.done;
    }

    protected int isDoneInt() {
        return this.done? 1 : 0;
    }

    /**
     * encodes the task to a more appropriate format for storage
     * @return the encoded version of the task
     */
    public abstract String encode();

    /**
     * returns a string representation of the task
     * @return string representation of the task
     */
    public String toString() {
        return "[" + this.getStatus() + "] " + this.task;
    }

    /**
     * returns the date of the task
     * @return date of the task
     */
    public abstract LocalDate getDate();
}
