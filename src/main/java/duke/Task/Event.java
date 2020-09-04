package duke.task;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task that is an event with both start and end time/date.
 */
public class Event extends Task {

    protected String at;

    private final LocalDate startDate;
    private LocalDate endDate;
    private final LocalTime startTime;
    private final LocalTime endTime;

    public static final String DELIMITER_AT = " /at ";

    /**
     * Constructs an <code>Event</code> Object to represent an event.
     *
     * @param description The description of the event item.
     * @param at          The duration of the event (including the start and end of both date and time).
     *                    If the start and end dates of the event are the same, only the start date
     *                    is required (e.g. '1/1/2020 1000-1100' means the event
     *                    starts on 1st January 2020, 10AM and ends at 11AM on the same day).
     *                    However, if the start and end dates of the event are different, the end date
     *                    should be specified (e.g. '1/1/2020-2/1/2020 0700-1800' means the event
     *                    starts on 1st January 2020 and ends on 2nd January 2020, from 7AM to 6PM).
     * @throws DukeException If the format of either date or time is incorrect and
     *                       if start time/date is before end time/date.
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        this.at = at;

        String[] dateTime = this.at.split(" ");
        try {
            if (dateTime[0].contains("-")) {
                String[] dateSplit = dateTime[0].split("-");
                this.startDate = parseDate(dateSplit[0]);
                this.endDate = parseDate(dateSplit[1]);

                if (this.startDate.compareTo(this.endDate) > 0) {
                    throw new DukeException("the start date cannot be after the end date.");
                }
            } else {
                this.startDate = parseDate(dateTime[0]); // case if event last for a day.
            }

            String[] timeSplit = dateTime[1].split("-");
            this.startTime = parseTime(timeSplit[0]);
            this.endTime = parseTime(timeSplit[1]);

            if (this.startTime.compareTo(this.endTime) > 0) {
                throw new DukeException("the start time cannot be after the end time.");
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("invalid time/date format!");
        }
    }

    /**
     * Parses a text and returns the date of an event.
     *
     * @param dateString The text to be parsed.
     * @return The date of an event.
     */
    private LocalDate parseDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        return LocalDate.parse(dateString, formatter);
    }

    /**
     * Parses a text and returns the time of an event.
     *
     * @param timeString The text to be parsed.
     * @return The time of an event.
     */
    private LocalTime parseTime(String timeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("Hmm");
        return LocalTime.parse(timeString, formatter);
    }

    private String atFormat() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEE, d MMMM yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mma");
        if (endDate == null) {
            return String.format("%s, %s - %s", dateFormatter.format(startDate),
                    timeFormatter.format(startTime),
                    timeFormatter.format(endTime));
        } else {
            return String.format("%s - %s, %s - %s", dateFormatter.format(startDate),
                    dateFormatter.format(endDate),
                    timeFormatter.format(startTime),
                    timeFormatter.format(endTime));
        }
    }

    /**
     * Converts an event into serialized form (e.g.
     * 'E | 0 | project meeting | 20/12/2020 200-1400').
     */
    @Override
    public String serialize() {
        return String.format("E | %d | %s | %s", getStatusCode(), description, at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + atFormat() + ")";

    }
}
