package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate date;
    protected LocalTime time;
    protected String by;

    /**
     * Returns deadline date and time as a String.
     * @return The deadline date and time as a String.
     */
    public String getBy() {
        return by;
    }

    /**
     * Initializes the Deadline with a description, LocalDate and LocalTime as its deadline.
     *
     * @param description The description of the deadline.
     * @param date The date of the deadline.
     * @param time The time of the deadline.
     */
    public Deadline(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
        this.by = convertDateAndTimeToString();
    }

    // Date time format is dd/MM/yyyy tttt
    String convertDateAndTimeToString() {
        String str = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " " + time;
        return str;
    }

    /**
     * Returns the LocalDate of the deadline.
     *
     * @return The LocalDate of the deadline.
     */
    public LocalDate getLocalDate() {
        return date;
    }

    /**
     * Returns the LocalTime of the deadline.
     *
     * @return The LocalTime of the deadline.
     */
    public LocalTime getLocalTime() {
        return time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() +  " (by: " + by + ")";
    }
}
