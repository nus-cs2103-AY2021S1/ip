package duke.tasks;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.exceptions.DukeInvalidDescriptionException;
import duke.exceptions.DukeNoDateException;
import duke.exceptions.DukeNoDescriptionException;


/**
 * Abstract Task class.
 * Child classes: Deadline, Event, Todo.
 */
public abstract class Task implements Comparable<Task> {

    public static final char DONE = '\u2713';
    public static final char NOT_DONE = '\u2717';

    private boolean isDone;

    private final String itemString;
    private String dateString;


    /**
     * Abstract constructor for Task.
     *
     * @param itemString Description string.
     */
    public Task(String itemString) {
        this.itemString = itemString;
        this.isDone = false;
    }


    /**
     * Abstract constructor for Task.
     *
     * @param itemString Description string.
     * @param isDone     True if task is done, false otherwise.
     */
    public Task(String itemString, boolean isDone) {
        this.itemString = itemString;
        this.isDone = isDone;
    }


    /**
     * Splits the itemString by the delimiter and returns the task portion.
     *
     * @param taskString Item string.
     * @param delimiter  Delimiter used.
     * @return Task portion of the string.
     */
    public static String parseTaskString(String taskString, String delimiter) throws DukeInvalidDescriptionException {
        try {
            return taskString.split(delimiter)[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeNoDescriptionException("Description text not given.");
        }
    }


    /**
     * Splits the taskString by the delimiter and returns the Date portion.
     *
     * @param taskString Item string.
     * @param delimiter  Delimiter used.
     * @return Date portion of the string.
     */
    public static String parseDateString(String taskString, String delimiter) throws DukeInvalidDescriptionException {
        try {
            return taskString.split(delimiter)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeNoDateException("Date not given in description text.");
        }
    }


    /**
     * Formats date string.
     *
     * @param date date object
     * @return formatted date string.
     */
    public static String formatDateString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }


    /**
     * Gets item string.
     *
     * @return Item string.
     */
    public String getItemString() {
        return this.itemString;
    }


    /**
     * Gets date string.
     *
     * @return Date string of task.
     */
    public String getDateString() {
        return this.dateString;
    }


    /**
     * Sets date string.
     *
     * @param dateString New date string to be set.
     */
    public void setDateString(String dateString) {
        this.dateString = dateString;
    }


    /**
     * Checks whether task is done.
     *
     * @return If task is done.
     */
    public boolean isDone() {
        return this.isDone;
    }


    /**
     * Marks this item as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }


    /**
     * Gets string array for storage.
     *
     * @return String array for storage.
     */
    public abstract String[] toStorageStringArr();


    /**
     * Checks if task matches keyword.
     *
     * @param keyword keyword to be searched for.
     * @return True if keyword is substring of itemString, false otherwise.
     */
    public boolean matches(String keyword) {
        return this.itemString.contains(keyword);
    }


    /**
     * Gets LocalDate object of task.
     *
     * @return LocalDate object of task.
     */
    public abstract LocalDate getDate();


    @Override
    public String toString() {
        char stateSymbol = this.isDone ? DONE : NOT_DONE;
        return String.format("[%s] %s", stateSymbol, this.itemString);
    }


    @Override
    public abstract int compareTo(Task t);

}
