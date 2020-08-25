package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task with description and by-time.
 * Inherits from Task.
 */
public class Deadline extends Task {
    protected String by;

    public Deadline(String desc, String by) {
        super(desc);
        this.by = by;
        parseDate(by);
    }

    /**
     * Process user input into into another datetime format.
     * e.g. 2020-10-10 1800 --> 10 Oct 2020 6:00 PM
     * if user input doesnt match this format then accept time as normal string.
     *
     * @param input User input for the time for deadline.
     */
    public void parseDate(String input) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime parsedDateTime = LocalDateTime.parse(input, formatter);
            this.by = parsedDateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy, hh:mm a"));
        } catch (DateTimeParseException e) {
            System.out.print("");
        }
    }

    /**
     * Converts the task to a string for saving.
     *
     * @return String representing a task in save file.
     */
    @Override
    public String toSaveFormat() {
        return "deadline " + super.toSaveFormat() + " /by " + by;
    }

    /**
     * Converts the task to a string indicating type of task, done, description and time (if applicable).
     *
     * @return String representing task in user interface.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
