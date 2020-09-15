package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * Encapsulates the date and any optional time parameter for a task.
 */
public class DateTime {

    private final LocalDate date;
    private final Optional<LocalTime> optionalTime;

    /**
     * Initialises with the date and any optional time parameter.
     *
     * @param date Date to be stored.
     * @param optionalTime Any time that is specified by the user.
     */
    public DateTime(LocalDate date, Optional<LocalTime> optionalTime) {
        this.date = date;
        this.optionalTime = optionalTime;
    }

    /**
     * Returns if supplied date is the same day as the date stored.
     *
     * @param date Date to be checked for equality.
     * @return True if both dates are referring to the same day.
     */
    public boolean checkDateEqual(LocalDate date) {
        return date.equals(this.date);
    }

    /**
     * Retrieves the formatted version of the date stored.
     * For printing to file.
     *
     * @return Formatted date.
     */
    public String getFileFormattedDate() {
        return this.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Retrieves the formatted version of the date and time stored.
     * For printing to file.
     *
     * @return Formatted date and time or date only if no time exists.
     */
    public String getFileFormattedDateTime() {
        return this.optionalTime.map(x -> getFileFormattedDate() + " | " + x.format(DateTimeFormatter.ofPattern(
                "HHmm"))).orElse(getFileFormattedDate());
    }

    /**
     * Retrieves the formatted version of the date stored.
     * For printing directly to the user.
     *
     * @return Formatted date.
     */
    public String getPrintFormattedDate() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Retrieves the formatted version of the time stored.
     * For printing directly to the user.
     *
     * @return Formatted time or empty string if no time exists.
     */
    public String getPrintFormattedTime() {
        return this.optionalTime.map(x -> x.format(DateTimeFormatter.ofPattern("HHmma"))).orElse("");
    }
}
