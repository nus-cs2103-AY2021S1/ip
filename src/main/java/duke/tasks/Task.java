package duke.tasks;

import duke.exceptions.DukeInvalidDescriptionException;
import duke.exceptions.DukeNoDateException;
import duke.exceptions.DukeNoDescriptionException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * Abstract Task class.
 * Child classes: Deadline, Event, Todo.
 */
public abstract class Task {

    public static final char DONE = '\u2713';
    public static final char NOT_DONE = '\u2717';

    public final String itemString;
    public String dateString;
    public boolean isDone;


    /**
     * Constructor for Task.
     *
     * @param itemString description string.
     */
    public Task(String itemString) {
        this.itemString = itemString;
        this.isDone = false;
    }


    /**
     * Constructor for Task.
     *
     * @param itemString description string.
     * @param isDone     whether this task is done.
     */
    public Task(String itemString, boolean isDone) {
        this.itemString = itemString;
        this.isDone = isDone;
    }


    /**
     * Splits the itemString by the delimiter and returns the task portion.
     *
     * @param taskString Item String.
     * @param delimiter  Delimiter used.
     * @return Task portion of the string.
     */
    public static String getTaskString(String taskString, String delimiter) throws DukeInvalidDescriptionException {
        try {
            return taskString.split(delimiter)[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeNoDescriptionException("Description text not given.");
        }
    }


    /**
     * Splits the taskString by the delimiter and returns the Date portion.
     *
     * @param taskString Item String.
     * @param delimiter  Delimiter used.
     * @return Date portion of the string.
     */
    public static String getDateString(String taskString, String delimiter) throws DukeInvalidDescriptionException {
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
     * Marks this item as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }


    /**
     * Gets string array for storage.
     *
     * @return string array for storage.
     */
    public String[] toStorageStringArr() {
        return new String[]{"Task", this.isDone ? "1" : "0", this.itemString};
    }


    @Override
    public String toString() {
        char stateSymbol = this.isDone ? DONE : NOT_DONE;
        return String.format("[%s] %s", stateSymbol, this.itemString);
    }

}
