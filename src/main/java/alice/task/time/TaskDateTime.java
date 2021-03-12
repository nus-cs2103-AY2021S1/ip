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
    private static final DateTimeFormatter TIME_FORMAT_SIMPLE = DateTimeFormatter
            .ofPattern("ha");
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter
            .ofPattern("h:mm a");
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter
            .ofPattern("EEEE, MMM dd uuuu");

    private final LocalDate date;
    private final LocalTime time;

    /**
     * Create a {@code TaskDateTime} that represents the date and time of a task.
     *
     * @param date the LocalDate containing the date.
     * @param time the LocalTime containing the time, can be null if no time is used.
     */
    private TaskDateTime(LocalDate date, LocalTime time) {
        this.date = date;
        this.time = time;
    }

    /**
     * Create a {@code TaskDateTime} that represents the date and time of a task.
     *
     * @param dateTime the {@code LocalDateTime} containing the datetime.
     */
    private TaskDateTime(LocalDateTime dateTime) {
        this(dateTime.toLocalDate(), dateTime.toLocalTime());
    }

    /**
     * Parses the datetime input given by the user into {@code TaskDateTime} which is recognisable by Alice.
     * The interface that is used to call
     *
     * @param dateTimeString the user input containing a date and time.
     * @return the {@code TaskDateTime} containing the datetime indicated by the user input.
     * @throws InvalidCommandException if the datetime input given by the user does not match any known patterns.
     */
    public static TaskDateTime parseDateTime(String dateTimeString) throws InvalidCommandException {
        return parseDateTimeHelper(dateTimeString, LocalDateTime.now());
    }

    /**
     * Parses the datetime input given by the user into {@code TaskDateTime} which is recognisable by Alice.
     * Contains the main implementation of parseDateTime.
     * Used for testing the implementation against a predefined currDateTime.
     *
     * @param dateTimeString the user input containing a date and time.
     * @param currDateTime   the current datetime to be referenced against. Usually contains {@code LocalDateTime.now()}
     * @return the {@code TaskDateTime} containing the datetime indicated by the user input.
     * @throws InvalidCommandException if the datetime input given by the user does not match any known patterns.
     */
    static TaskDateTime parseDateTimeHelper(String dateTimeString, LocalDateTime currDateTime)
            throws InvalidCommandException {
        assert !dateTimeString.isBlank() : "dateTimeString should not be empty using parseDateTime";

        LocalDateTime hybridNaturalDt = DateParser.parseSpecial(dateTimeString, currDateTime);
        if (hybridNaturalDt != null) {
            return new TaskDateTime(hybridNaturalDt);
        }

        // If input contain only one type - date or time
        if (!dateTimeString.contains(" ")) {
            // Check for possible date format/language
            try {
                LocalDate possibleDate = DateParser.parse(dateTimeString, currDateTime.toLocalDate());
                return new TaskDateTime(possibleDate, null);
            } catch (InvalidCommandException ex) {
                // fallthrough, check time next.
            }

            // No date format/language
            // Check for possible time format/language
            try {
                LocalTime possibleTime = TimeParser.parse(dateTimeString);
                LocalTime currTime = currDateTime.toLocalTime();
                if (possibleTime.isAfter(currTime)) {
                    // Use current day with implied time
                    return new TaskDateTime(currDateTime.toLocalDate(), possibleTime);
                } else {
                    // Use current day + 1 with implied time
                    return new TaskDateTime(currDateTime.toLocalDate().plusDays(1), possibleTime);
                }
            } catch (InvalidCommandException ex) {
                // Exception thrown if time parsing fails as no other possible combination accepted.
                throw new InvalidCommandException("Invalid datetime! Please use a recognisable date or time format");
            }
        }

        // Splits the dateTimeString into their respective dateString and timeString.
        String[] splitStrings = dateTimeString.strip().split(" ", 2);
        String dateString = splitStrings[0];
        String timeString = splitStrings[1];

        // Parses date and time individually
        LocalDate date = DateParser.parse(dateString, currDateTime.toLocalDate());
        LocalTime time = TimeParser.parse(timeString);
        return new TaskDateTime(date, time);
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
            if (encodedDateTime.contains("T")) {
                // Use ISO_LOCAL_DATE_TIME
                return new TaskDateTime(LocalDateTime.parse(encodedDateTime));
            } else {
                // Only contains date
                // Use ISO_LOCAL_DATE
                return new TaskDateTime(LocalDate.parse(encodedDateTime), null);
            }
        } catch (DateTimeParseException ex) {
            throw new AliceException("Corrupted datetime data");
        }
    }

    /**
     * Encode the {@code LocalDateTime} into ISO_LOCAL_DATE_TIME or ISO_LOCAL_DATE format for standardisation.
     * ISO_LOCAL_DATE_TIME is used when time is included in {@code TaskDateTime}, otherwise ISO_LOCAL_DATE is used.
     *
     * @return the string representation of {@code LocalDateTime} in the appropriate format.
     */
    public String encode() {
        if (time == null) {
            return date.format(DateTimeFormatter.ISO_LOCAL_DATE);
        } else {
            return date.format(DateTimeFormatter.ISO_LOCAL_DATE) + "T" + time.format(DateTimeFormatter.ISO_LOCAL_TIME);
        }
    }

    String toStringBasedOn(LocalDate currDate) {
        StringBuilder resultingFormat = new StringBuilder();
        if (date.isEqual(currDate)) {
            resultingFormat.append("Today");
        } else {
            resultingFormat.append(date.format(DATE_FORMAT));
        }

        if (time == null) {
            // No time format
            return resultingFormat.toString();
        } else if (time.getMinute() == 0) {
            // display time in the shorthand form, eg. 1pm
            resultingFormat.append(", ").append(time.format(TIME_FORMAT_SIMPLE));
            return resultingFormat.toString();
        } else {
            // has minute, return time in the form, eg. 12:35 am
            resultingFormat.append(", ").append(time.format(TIME_FORMAT));
            return resultingFormat.toString();
        }
    }

    @Override
    public String toString() {
        return toStringBasedOn(LocalDate.now());
    }
}
