package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * DateTime class used to store date objects.
 */
public class DateTime {
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private LocalDate date;

    /**
     * Constructor for the DateTime class.
     *
     * @param date Defaults to today if the date passed in is invalid.
     */
    public DateTime(String date) {
        assert (isValidFormat(date));
        this.date = LocalDate.parse(date);
    }

    /**
     * Check if the input date is of a valid format.
     *
     * @param date Input date.
     * @return A boolean to show whether the input date is valid.
     */
    public static boolean isValidFormat(String date) {
        try {
            FORMATTER.parse(date);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Function that converts the date to the proper format to which it should be saved.
     *
     * @return A String of the date in proper format.
     */
    public String getSaveString() {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Overrides the standard toString method.
     *
     * @return A String describing the date in the MMM d yyyy format.
     */
    @Override
    public String toString() {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
