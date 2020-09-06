package duke.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Class to hold datetime information.
 */
public class DateTime {
    private String originalInput;
    private LocalDate date;
    private LocalTime time;

    /**
     * Creates a new {@code DateTime} that only contains a date.
     * @param originalInput Original datetime input.
     * @param date Parsed date.
     */
    public DateTime(String originalInput, LocalDate date) {
        this.originalInput = originalInput;
        this.date = date;
    }

    /**
     * Creates a new {@code DateTime} that contains both a date and time.
     * @param originalInput Original datetime input.
     * @param date Parsed date.
     * @param time Parsed time.
     */
    public DateTime(String originalInput, LocalDate date, LocalTime time) {
        this(originalInput, date);
        this.time = time;
    }

    public String getOriginalInput() {
        return originalInput;
    }

    @Override
    public String toString() {
        String output = date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));

        if (time != null) {
            output += ", " + time.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT));
        }

        return output;
    }
}
