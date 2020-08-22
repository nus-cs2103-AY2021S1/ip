package duke.Task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected String by;

    private final LocalDate date;
    private final LocalTime time;

    public static final String delimiterBy = " /by ";

    /** Constructs a <code>Deadline</code> Object to represent a task with a deadline
     *
     * @param description The description of the deadline
     * @param by The deadline containing date and time
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;

        String[] dateTime = this.by.split(" ");
        this.date = parseDate(dateTime[0]);
        if (dateTime.length == 1) {
            this.time = null;
        } else {
            this.time = parseTime(dateTime[1]);
        }
    }

    /**
     * Parses a text and returns the date of the deadline
     *
     * @param dateString The text to be parsed
     * @return The date of the deadline
     */
    private LocalDate parseDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        return LocalDate.parse(dateString, formatter);
    }

    /**
     * Parses a text and returns the time of the deadline
     *
     * @param timeString The text to be parsed
     * @return The time of the deadline
     */
    private LocalTime parseTime(String timeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("Hmm");
        return LocalTime.parse(timeString, formatter);
    }

    private String byFormat() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEE, d MMMM yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mma");

        if (this.time == null) {
            return String.format("%s", dateFormatter.format(date));
        } else {
            return String.format("%s, %s", dateFormatter.format(date), timeFormatter.format(time));
        }
    }

    @Override
    public String serialize() {
        return String.format("D | %d | %s | %s", getStatusCode(), description , by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byFormat() + ")";
    }
}