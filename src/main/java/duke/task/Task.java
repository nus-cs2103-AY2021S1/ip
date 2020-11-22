package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String description;
    protected boolean isDone;


    /**
     * Creates a task.
     *
     * @param description the content of the class.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Calls to get the status icon.
     *
     * @return tick or X symbols.
     */
    protected String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }


    /**
     * Changes the state of this task, isDone to true.
     *
     * @return the task after marking it as done.
     */
    public Task markAsDone() {
        isDone = true;
        return this;
    }


    /**
     * Overrides the toString method.
     *
     * @return a custom event description.
     */
    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + description;
    }


    /**
     * Returns a fixed format in string to store the task to storage.
     *
     * @return T | 0 | return book.
     */
    public String toCustomString() {
        return "T | " + (isDone ? 1 : 0) + " | " + description;
    }


    /**
     * Gets the content of the tasks.
     *
     * @return String the content of the tasks.
     */
    public String getDescription() {
        return description;
    }


    /**
     * Returns the converted date in LocalDate format.
     *
     * @param toBeConverted the string to be converted.
     * @return the date in LocalDate format.
     */
    protected LocalDate convertToLocalDate(String toBeConverted) {
        return LocalDate.parse(toBeConverted.trim());
    }


    /**
     * Converts the local date to string.
     *
     * @param date a LocalDate instance.
     * @return the date in string.
     */
    protected String convertToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM dd yyy"));
    }


}
