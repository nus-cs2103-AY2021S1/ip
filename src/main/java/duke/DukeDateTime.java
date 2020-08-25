package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * The DukeDateTime class keeps track of a LocalDateTime
 * Only keep track of day, month, year, hour and minutes
 * Predefined formats: DukeDateTime.FORMAT, DukeDateTime.PRETTY
 */
public class DukeDateTime implements Comparable<DukeDateTime> {

    public static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("ddMMyyyy HHmm");
    public static final DateTimeFormatter PRETTY = DateTimeFormatter.ofPattern("dd MMM yyyy KK:mma");

    private final LocalDateTime localDateTime;

    /**
     * Initialise DukeDateTime with current date and time
     */
    public DukeDateTime() { this(LocalDateTime.now().format(FORMAT)); }

    /**
     * Initialise DukeDateTime with specified time, following DukeDateTime.FORMAT format
     * @param localDateTime The string representation of LocalDateTime in DukeDateTime.FORMAT format
     * @throws DateTimeParseException If the String cannot be parsed
     */
    public DukeDateTime(String localDateTime) throws DateTimeParseException {
        this.localDateTime = LocalDateTime.parse(localDateTime, FORMAT);
    }

    /**
     * Initialise DukeDateTime with specified LocalDateTime
     * @param localDateTime The LocalDateTime to initialize with
     */
    public DukeDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    /**
     * Get the embedded localDateTime object
     * @return The localDateTime object referenced by dukeDateTime
     */
    public LocalDateTime get() {
        return this.localDateTime;
    }

    /**
     * Get a pretty print of DukeDateTime
     * Format is specified in DukeDateTime.PRETTY
     * (i.e. dd MMM yyyy KK:mma)
     * (e.g. 18 May 2020 08:20pm)
     *
     * @return The pretty print of DukeDateTime
     */
    public String pretty() {
        return this.localDateTime.format(PRETTY);
    }

    /**
     * Get the String format of DukeDateTime
     * Format is specified in DukeDateTime.FORMAT
     * (i.e. ddMMyyyy HHmm)
     * (e.g. 22082020 1800)
     *
     * @return The String representation of DukeDateTime
     */
    @Override
    public String toString() {
        return this.localDateTime.format(FORMAT);
    }

    /**
     * Compares two DukeDateTime Object
     *
     * @param obj The other DukeDateTime
     * @return -1 if this is smaller, 1 if this is bigger, or 0 if they are similar
     */
    @Override
    public int compareTo(DukeDateTime obj) {
        return this.localDateTime.compareTo(obj.get());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof DukeDateTime)) return false;
        DukeDateTime that = (DukeDateTime) obj;
        return localDateTime.equals(that.localDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(localDateTime);
    }
}
