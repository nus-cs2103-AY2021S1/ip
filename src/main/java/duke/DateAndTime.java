package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.Locale;

import duke.exception.InvalidDateTimeException;

/**
 * Represents date and time object
 */
public class DateAndTime {
    private final LocalDate date;
    private final LocalTime time;

    /**
     * Creates a DateAndTime object
     * @param date  String representing date
     * @param time  String representing time
     * @throws InvalidDateTimeException
     */
    public DateAndTime(String date, String time) throws InvalidDateTimeException {
        try {
            this.date = LocalDate.parse(date);
            this.time = LocalTime.parse(time);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException();
        }
    }

    /**
     * Get date
     * @return  String of date
     */
    public String getDate() {
        return this.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Get time
     * @return  String of time
     */
    public String getTime() {
        return this.time.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    @Override
    public String toString() {
        return this.date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(Locale.UK))
                + " " + this.time.format(DateTimeFormatter.ofPattern("hh:mma"));
    }

}
