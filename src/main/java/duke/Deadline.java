package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * The Deadline class represents tasks that have a deadline.
 */
public class Deadline extends Task {

    /**
     * Constructor which takes in a description of the task name and stores a LocalDate.
     *
     * @param description name of the task
     * @param date date of deadline
     */
    public Deadline(String description, LocalDate date) {
        super(description);
        symbol = "[D]";
        this.date = date;
    }

    /**
     * Constructor which takes in a description of the task name
     * and stores a LocalDateTime for time-specific tasks.
     *
     * @param description name of the task
     * @param dateTime date and time of deadline
     */
    public Deadline(String description, LocalDateTime dateTime) {
        super(description);
        symbol = "[D]";
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return (getSymbol() + super.toString() + " (by: " + getDate() + ")");
    }
}
