package misc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The DukeDateTime class keeps track of a LocalDateTime
 */
public class DukeDateTime implements Comparable<DukeDateTime> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("ddMMyyyy HHmm");
    private final LocalDateTime localDateTime;

    private DukeDateTime(LocalDateTime localDate) {
        this.localDateTime = localDate;
    }

    public LocalDateTime get() {
        return this.localDateTime;
    }

    /**
     * Generate a DukeDate representing a specified datetime
     * e.g. "01112020 1800" means "01 Nov 2020 06:00pm"
     *
     * @param dateTime The dateTime in the format "ddMMyyyy HHmm"
     * @return A DukeDateTime representing the specified dateTime
     * @throws DateTimeParseException If the dateTime does not follow the specified format
     */
    public static DukeDateTime generate(String dateTime) throws DateTimeParseException {
        // Build a localDateTime
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, FORMATTER);
        return new DukeDateTime(localDateTime);
    }

    /**
     * Get a pretty print of DukeDate
     * Format is "dd MMM yyyy KK:mma"
     * (e.g. 18 May 2020 08:20pm)
     *
     * @return The date represented by DukeDate
     */
    public String pretty() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy KK:mma");
        return this.localDateTime.format(formatter);
    }

    /**
     * Converts DukeDateTime to its String representation
     *
     * The String representation can be fed to DukeDateTime.generate
     * to reobtain the original DukeDateTime
     *
     * @return The String representation of this object
     */
    @Override
    public String toString() {
        return this.localDateTime.format(FORMATTER);
    }

    @Override
    public int compareTo(DukeDateTime o) {
        return this.localDateTime.compareTo(o.get());
    }
}
