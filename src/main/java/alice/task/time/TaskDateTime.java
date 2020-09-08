package alice.task.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import alice.AliceException;
import alice.command.InvalidCommandException;

/**
 * Represents a LocalDateTime in Alice.
 */
public class TaskDateTime {
    private static final DateTimeFormatter DATETIME_FORMAT_SIMPLE = DateTimeFormatter
            .ofPattern("EEEE, MMM dd uuuu, ha");
    private static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter
            .ofPattern("EEEE, MMM dd uuuu, h:mm a");
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter
            .ofPattern("EEEE, MMM dd uuuu");

    private final LocalDateTime dateTime;
    private final boolean hasTime;
    private final boolean hasMinute;

    /**
     * Create an {@code TaskDateTime} that represents the dateTime of a task.
     *
     * @param dateTime the LocalDateTime containing the dateTime.
     */
    private TaskDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        this.hasTime = !dateTime.toLocalTime().equals(LocalTime.MIDNIGHT);
        this.hasMinute = dateTime.toLocalTime().getMinute() != 0;
    }

    /**
     * Parses the datetime input given by the user into {@code TaskDateTime} which is recognisable by Alice.
     *
     * @param dateTimeString the user input containing a date and time.
     * @return the {@code TaskDateTime} containing the datetime indicated by the user input.
     * @throws InvalidCommandException if the datetime input given by the user does not match any known patterns.
     */
    public static TaskDateTime parseDateTime(String dateTimeString) throws InvalidCommandException {
        // Splits the dateTimeString into their respective dateString and timeString
        String[] splitStrings = dateTimeString.strip().split(" ", 2);
        String dateString = splitStrings[0];
        String timeString = splitStrings.length == 2 ? splitStrings[1] : "";

        LocalDate date = DateParser.parse(dateString);
        LocalTime time = TimeParser.parse(timeString);
        return new TaskDateTime(LocalDateTime.of(date, time));
    }

    /**
     * Decode an encoded string representation of the {@code TaskDateTime}.
     *
     * @param encodedDateTime the encoded string of the task's datetime.
     * @return the {@code TaskDateTime} containing the task's decoded datetime.
     * @throws AliceException if the encoded string is corrupted.
     */
    public static TaskDateTime decode(String encodedDateTime) throws AliceException {
        try {
            return new TaskDateTime(LocalDateTime.parse(encodedDateTime));
        } catch (DateTimeParseException ex) {
            throw new AliceException("Corrupted datetime data");
        }
    }

    public String encode() {
        return dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    @Override
    public String toString() {
        if (hasMinute) {
            assert dateTime.toLocalTime().getMinute() != 0 : "Supposed to have time input";
            return dateTime.format(DATETIME_FORMAT);
        } else if (hasTime) {
            assert !dateTime.toLocalTime().equals(LocalTime.MIDNIGHT) : "Supposed to have time input";
            return dateTime.format(DATETIME_FORMAT_SIMPLE);
        } else {
            assert dateTime.toLocalTime().equals(LocalTime.MIDNIGHT) : "Not supposed to have time input";
            return dateTime.format(DATE_FORMAT);
        }
    }
}
