package duke.task;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {

    protected String by;

    private final LocalDate date;
    private final LocalTime time;

    public static final String DELIMITER_BY = " /by ";

    /**
     * Constructs a <code>Deadline</code> Object to represent a task with a deadline.
     *
     * @param description The description of the deadline.
     * @param by The deadline which contains a date and time (e.g.
     *           '01/01/2020 1800' means 1st January 2019, 6pm). The time
     *           can be omitted. The date input must follow d/m/yyyy format
     *           and the time must follow Hmm format if it exists.
     * @throws DukeException If invalid time format is passed in.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.by = by;
        try {
            String[] dateTime = this.by.split(" ");
            this.date = parseDate(dateTime[0]);
            if (dateTime.length == 1) {
                this.time = null;
            } else {
                this.time = parseTime(dateTime[1]);
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("invalid time/date format!");
        }
    }

    /**
     * Parses a text and returns the date of the deadline.
     *
     * @param dateString The text to be parsed.
     * @return The date of the deadline.
     */
    private LocalDate parseDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        return LocalDate.parse(dateString, formatter);
    }

    /**
     * Parses a text and returns the time of the deadline.
     *
     * @param timeString The text to be parsed.
     * @return The time of the deadline.
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

    /**
     * Converts a deadline into serialized form (e.g.
     * 'D | 0 | return book | 19/09/2020 1000').
     */
    @Override
    public String serialize() {
        return String.format("D | %d | %s | %s", getStatusCode(), description, by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byFormat() + ")";
    }
}
