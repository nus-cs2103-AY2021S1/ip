package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/** This class represents a Deadline. */
public class Deadline extends Task {
    protected LocalDate date;
    protected LocalTime time;

    /**
     * Constructs a Deadline object associated with a description and a date.
     * @param description The description of the task.
     * @param date The date of the deadline.
     */
    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    /**
     * Constructs a Deadline object associated with a description, a date and a time.
     * @param description The description of the task.
     * @param date The date of the deadline.
     * @param time The time of the deadline.
     */
    public Deadline(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    public LocalDate getDate() {
        return this.date;
    }

    /**
     * @return A String representing the Deadline object, to be used when saving deadlines to the storage file.
     */
    @Override
    public String txtFileFormat() {
        return "D ~/~ " + super.txtFileFormat() + " ~/~ " + this.date.toString()
                + (this.time != null ? " ~/~ " + this.time.toString() : "");
    }

    /**
     * @return A String representing the Deadline object, to be used when printing the Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + (time != null ? " " + time.format(DateTimeFormatter.ofPattern("hh:mm a")) : "") + ")";
    }
}
