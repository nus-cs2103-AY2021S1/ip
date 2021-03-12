package junimo.task;

import junimo.task.exception.JunimoStartEndDateTimeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline object which inherits from Task.
 */
public class Event extends Task {
    public static final DateTimeFormatter PARSER_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma");
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("h:mma");

    protected LocalDateTime startAt;
    protected LocalDateTime endAt;

    /**
     * Constructs an instance of Event object.
     * @param description Description of event.
     * @param startAt starting date and time of event.
     * @param endAt ending date and time of event.
     * @param isDone Whether event is done or not.
     */
    public Event(String description, String startAt, String endAt, boolean isDone) {
        super(description, isDone);
        try {
            this.startAt = LocalDateTime.parse(startAt, PARSER_FORMATTER);
            this.endAt = LocalDateTime.parse(endAt, PARSER_FORMATTER);
            checkDateTimes(this.startAt, this.endAt);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("OOPS! The deadline in an incorrect format! "
                    + "Please indicate the date and time as <yyyy-MM-dd HH:mm>");
        }
    }

    private void checkDateTimes(LocalDateTime startAt, LocalDateTime endAt) {
        if (startAt.isAfter(endAt)) {
            throw new JunimoStartEndDateTimeException();
        }
    }

    /**
     * Implements method specified by abstract class Task.
     * @return String of Event object in format for saving to and retrieving from hard disk.
     */
    public String getParsedTask() {
        return "event " + description + " /start " + startAt.format(PARSER_FORMATTER) + " /end "
                + endAt.format(PARSER_FORMATTER) + System.lineSeparator() + isDone + System.lineSeparator();
    }

    @Override
    public String toString() {
        if (startAt.toLocalDate().equals(endAt.toLocalDate())) {
            return "[E]" + super.toString() + " (at: " + startAt.format(DATE_TIME_FORMATTER)
                    + " to " + endAt.toLocalTime().format(TIME_FORMATTER) + ")";
        } else {
            return "[E]" + super.toString() + " (at: " + startAt.format(DATE_TIME_FORMATTER)
                    + " to " + endAt.format(DATE_TIME_FORMATTER) + ")";
        }
    }

    /**
     * Overrides Object and Task equals method.
     * @param other Object compared to.
     * @return True if other is also an Event object with the same description, at and isDone fields. False otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof Event) {
            Event otherEvent = (Event) other;
            return description.equals(otherEvent.description)
                    && isDone == otherEvent.isDone
                    && startAt.equals(otherEvent.startAt)
                    && endAt.equals(otherEvent.endAt);
        } else {
            return false;
        }
    }
}
