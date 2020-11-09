package duke.core.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * DukeDateTime provides methods to convert String to LocalDateTime and vice versa
 * Predefined formats:
 *  - DukeDateTime.DEFAULT ("ddMMyyyy HHmm")
 *  - DukeDateTime.PRETTY ("dd MMM yyyy KK:mma")
 */
public class DukeDateTime {

    public static final DateTimeFormatter DEFAULT = DateTimeFormatter.ofPattern("ddMMyyyy HHmm");
    public static final DateTimeFormatter PRETTY = DateTimeFormatter.ofPattern("dd MMM yyyy KK:mma");

    private final LocalDateTime localDateTime;

    /**
     * Construct a DukeDateTime based on current time
     */
    public DukeDateTime() {
        this.localDateTime = DukeDateTime.trim(LocalDateTime.now());
    }

    /**
     * Construct a DukeDateTime based on given String
     * @param dateTime A String in DukeDateTime.DEFAULT format ("ddMMyyyy HHmm")
     */
    public DukeDateTime(String dateTime) {
        assert dateTime != null;
        this.localDateTime = LocalDateTime.parse(dateTime, DukeDateTime.DEFAULT);
    }

    /**
     * Removes unused attributes, leaving only the day, month, year, hour, and minute
     * @param localDateTime The LocalDateTime to be trimmed
     * @return A LocalDateTime with only day, month, year, hour and minute attributes
     */
    private static LocalDateTime trim(LocalDateTime localDateTime) {
        assert localDateTime != null;
        return LocalDateTime.parse(localDateTime.format(DukeDateTime.DEFAULT), DukeDateTime.DEFAULT);
    }

    /**
     * Convert a LocalDateTime into a String (Suitable for displaying)
     * The String will be converted to DukeDateTime.PRETTY format
     * (i.e. dd MMM yyyy KK:mma)
     * (e.g. 18 May 2020 08:20pm)
     *
     * @return The String representation in DukeDateTime.PRETTY format
     */
    public String pretty() {
        return localDateTime.format(DukeDateTime.PRETTY);
    }

    /**
     * Convert a LocalDateTime into a String
     * The String will be converted to DukeDateTime.DEFAULT format
     * (i.e. ddMMyyyy HHmm)
     * (e.g. 22082020 1800)
     *
     * @return The String representation in DukeDateTime.DEFAULT format
     */
    @Override
    public String toString() {
        return localDateTime.format(DukeDateTime.DEFAULT);
    }

    /**
     * Two DukeDateTime are equal if they refer to the same
     * year, month, day, hour and minute
     * @param obj The other object to compare to
     * @return true if they are equivalent. Otherwise, false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DukeDateTime)) {
            return false;
        }
        DukeDateTime that = (DukeDateTime) obj;
        return localDateTime.equals(that.localDateTime);
    }

    /**
     * @return The hashCode of a LocalDateTime which contains only the year,
     * month, day, hours and minutes
     */
    @Override
    public int hashCode() {
        return Objects.hash(localDateTime);
    }
}
