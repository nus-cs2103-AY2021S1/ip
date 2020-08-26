package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * <h>duke.Deadline duke.Task Type</h>
 * This is a type of tasks that need to be done before a specific date/time.
 */
public class Deadline extends Task {

    protected LocalDate date;
    protected LocalTime time;

    /**
     * Constructor for the deadline task.
     * @param description Name of the task input by user.
     * @param by Date of the task is due.
     */
    public Deadline(String description, String by) {
        super(description);
        String[] str = by.split(" ", 2);
        this.date = LocalDate.parse(str[0]);
        this.time = LocalTime.parse(str[1]);
    }

    /**
     * Getter for the date of the task.
     * @return LocalDate Date of the deadline.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Getter for the time of the deadline.
     * @return LocalTime Time of the deadline that is due.
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * Overridden toString method to output name, type and status of task.
     * @return String This returns a string.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + getDate().format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ", "
                + getTime().format(DateTimeFormatter.ofPattern("hh:mm a")) + ")";
    }
}
