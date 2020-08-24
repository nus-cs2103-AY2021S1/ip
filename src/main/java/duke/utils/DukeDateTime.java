package duke.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Represents a wrapper class over {@link LocalDateTime} such that some objects may not have time. */
public class DukeDateTime implements Comparable<DukeDateTime> {

    private LocalDateTime dateTime;
    private boolean containsTime;

    /** Constructs a DukeDateTime object with the specified dateTime and whether it contains a time.
     *
     * @param dateTime The LocalDateTime involved. If time is not involved, this dateTime will represent
     *                 the equivalent DateTime at midnight.
     * @param containsTime True if this object contains a time and false otherwise.
     */
    public DukeDateTime(LocalDateTime dateTime, boolean containsTime) {
        this.dateTime = dateTime;
        this.containsTime = containsTime;
    }

    /** Returns the String representation of this DukeDateTime object, including time
     * only if time is involved.
     *
     * @return The String representation of this DukeDateTime object.
     */
    @Override
    public String toString() {
        return containsTime
                ? dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a"))
                : dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    /** Tests if another DukeDateTime object is of the same date as this object.
     *
     * @param other The other DukeDateTime object to be compared with.
     * @return True if they are of the same date and false otherwise.
     */
    public boolean isSameDate(DukeDateTime other) {
        return dateTime.getYear() == other.dateTime.getYear()
                && dateTime.getMonthValue() == other.dateTime.getMonthValue()
                && dateTime.getDayOfMonth() == other.dateTime.getDayOfMonth();
    }

    /** Compares this DukeDateTime object with another DukeDateTime object in chronological order.
     *
     * @param o The other DukeDateTime object.
     * @return -1 if this object is before, 1 if this is after and 0 if this object is at the same
     * time as the other object.
     */
    @Override
    public int compareTo(DukeDateTime o) {
        if (dateTime.isBefore(o.dateTime)) {
            return -1;
        } else if (dateTime.isAfter(o.dateTime)) {
            return 1;
        } else {
            return 0;
        }
    }
}
