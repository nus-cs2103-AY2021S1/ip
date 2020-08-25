package main.java.duke.task;

import main.java.duke.DukeException;

import java.time.LocalDate;

/**
 * Represents a task added by the user, which may be done or not done.
 */
public abstract class Task {
    private String title;
    private boolean isDone;

    /**
     * Class constructor.
     *
     * @param title the content of the task
     */
    public Task(String title) {
        this.title = title;
        this.isDone = false;
    }

    /**
     * Class constructor.
     *
     * @param title  the content of the task
     * @param isDone whether or not the task is marked as completed
     */
    public Task(String title, boolean isDone) {
        this.title = title;
        this.isDone = isDone;
    }

    /**
     * Marks the task as completed.
     *
     * @return the task
     * @throws DukeException if the task is already completed
     */
    public Task markAsDone() throws DukeException {
        if (this.isDone) {
            throw new DukeException("Already marked as done.");
        } else {
            this.isDone = true;
            return this;
        }
    }

    public String toString() {
        String status = this.isDone ? "✓" : "✗";
        return "[" + status + "] " + this.title;
    }

    /**
     * Returns a string representation of the task to be used in saving the task to a file.
     *
     * @return string representation of the task
     */
    public String print() {
        String status = this.isDone ? "1" : "0";
        return status + " | " + this.title;
    }

    /**
     * Returns the date by or at which this task takes place, if any.
     *
     * @return a date
     */
    public abstract LocalDate getDate();

    public boolean containsKeyword(String keyword) {
        return this.title.contains(keyword);
    }
}
