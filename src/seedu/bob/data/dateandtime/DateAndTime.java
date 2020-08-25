package seedu.bob.data.dateandtime;

import seedu.bob.exceptions.BobInvalidDateAndTimeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.Locale;


/**
 * Represents a date and time for events/deadlines.
 */
public class DateAndTime {
    private final LocalDate date;
    private final LocalTime time;

    /**
     * Creates a DateAndTime.
     *
     * @param date String representing date.
     * @param time String representing time.
     * @throws BobInvalidDateAndTimeException If date and time parsed is not valid.
     */
    public DateAndTime(String date, String time) throws BobInvalidDateAndTimeException{
        try {
            this.date = LocalDate.parse(date);
            this.time = LocalTime.parse(time);
        } catch (DateTimeParseException e) {
            throw new BobInvalidDateAndTimeException();
        }
    }

    /**
     * Gets String value of date.
     *
     * @return String of date.
     */
    public String getDate() {
        return this.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Gets String value of time.
     *
     * @return String of time.
     */
    public String getTime() {
        return this.time.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    @Override
    public String toString() {
        return this.date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(Locale.UK))
                + ", " + this.time.format(DateTimeFormatter.ofPattern("hh:mma"));
    }
}
