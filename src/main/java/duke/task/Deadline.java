package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task that contains both task name and a date to serve as a deadline.
 * Time can be added as an optional parameter.
 */
public class Deadline extends Task {

    /** LocalDate object to store the date of the deadline. */
    private LocalDate date;

    /** LocalTime object to store the time of the deadline. */
    private LocalTime time;

    /**
     * Creates a Deadline task containing the description, date and time of the task.
     *
     * @param name Description of the deadline task.
     * @param date Date of the task deadline.
     * @param time Time of the task deadline.
     */
    public Deadline(String name, LocalDate date, LocalTime time) {
        super(name);
        this.time = time;
        this.date = date;
    }

    /**
     * Creates a Deadline task containing the description and date of the task.
     *
     * @param name Description of the deadline task.
     * @param date Date of the task deadline.
     */
    public Deadline(String name, LocalDate date) {
        super(name);
        time = null;
        this.date = date;
    }

    /**
     * Returns the string representation of a Deadline task to be appended to a local file.
     *
     * @return String representation of a Deadline task with description, done status, date and/or time.
     */
    public String appendFile() {
        String doneString = (isDone() == true ? "1" : "0");
        String time = this.time != null ? this.time.format(DateTimeFormatter.ofPattern("HHmm")) : "";
        return "deadline" + " | " + doneString + " | " + getName() + " | " + date + " | " + time;
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
        String dateFormat = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + "[" + doneString + "] " + getName() + " (by: " + dateFormat
                + (time != null ? " " + time.format(DateTimeFormatter.ofPattern("HHmma")) + " " : "") + ")";
    }
}

