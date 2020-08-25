package main.java.duke.task;

import main.java.duke.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * Represents Task that the user wants Duke to remember
 */
public abstract class Task {

    protected static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");
    protected static final DateTimeFormatter SAVE_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    protected static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    private final String name;
    protected Optional<LocalDate> date;
    private boolean isDone;

    protected Task(String name) {
        this.name = name;
        this.isDone = false;
        this.date = Optional.empty();
    }

    /**
     * Assign a date to Task.
     * @param date Date for the Task is set to.
     */
    public void setDate(LocalDate date) {
        this.date = Optional.of(date);
    }

    /**
     * Return the task's date.
     * @return the date if date is set. Else, return Optional.empty.
     */
    public Optional<LocalDate> getDate() {
        return this.date;
    }

    /**
     * Set the Task as done.
     * @throws DukeException If a done-task is set done again.
     */
    public void setDone() throws DukeException {
        if (isDone) {
            throw new DukeException("Task is already done");
        }
        this.isDone = true;
    }

    /**
     * Convert Task into the saved format.
     * @return A String that described the Task in saved format.
     */
    public String toSaveFormat() {
        String status = isDone ? "1" : "0";
        return " | " + status + " | " + name ;
    }

    /**
     * Convert Task into the display format.
     * @return A String that described the Task in display format.
     */
    @Override
    public String toString() {
        //String icon = isDone ? "\u2713" : "\u2718";
        String icon = isDone ? "X" : " ";
        return "[" + icon + "] " + name;
    }
}
