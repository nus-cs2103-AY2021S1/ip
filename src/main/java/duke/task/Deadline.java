package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import duke.DateTime;

/**
 * Represents a Deadline task that contains both task name and a date to serve as a deadline.
 * Time can be added as an optional parameter.
 */
public class Deadline extends Task {

    /** DateTime object to store the date and any specified time of the deadline. */
    private DateTime dateTime;

    /**
     * Creates a Deadline task containing the description, date and time of the task.
     *
     * @param name Description of the deadline task.
     * @param dateTime Date and any specified time of the task deadline.
     */
    public Deadline(String name, DateTime dateTime) {
        super(name);
        this.dateTime = dateTime;
    }

    /**
     * Returns the string representation of a Deadline task to be appended to a local file.
     *
     * @return String representation of a Deadline task with description, done status, date and/or time.
     */
    public String appendFile() {
        String doneString = (isDone() == true ? "1" : "0");
        return "deadline" + " | " + doneString + " | " + getName() + " | " + dateTime.getFileFormattedDateTime();
    }

    /**
     * Returns the string representation of a Deadline task.
     *
     * @return String representation of a Deadline task containing the description, done status, formatted date and/or
     * time.
     */
    @Override
    public String toString() {
        String doneString = (isDone() == true ? "✓" : "✗");
        return "[D]" + "[" + doneString + "] " + getName() + " (by: " + dateTime.getPrintFormattedDate()
                + " " + dateTime.getPrintFormattedTime() + ")";
    }
}

