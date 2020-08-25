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
     * @param date String representing date.
     * @param time String representing time.
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
     * Gets original String value of date.
     * @return Original String of date.
     */
    public String getDate() {
        return this.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Gets original String value of time.
     * @return Original String of time.
     */
    public String getTime() {
        return this.time.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    /**
     * Overridden toString method.
     * @return String value of DateAndTime.
     */
    @Override
    public String toString() {
        return this.date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(Locale.UK))
                + ", " + this.time.format(DateTimeFormatter.ofPattern("hh:mma"));
    }
}
