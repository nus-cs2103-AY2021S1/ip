package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a wrapper class over {@link LocalDateTime} such that some objects may not contain time.
 */

public class DukeDateTime {
    private LocalDateTime dateTime;
    private boolean hasTime;

    /**
     * Creates a DukeDateTime object with the inputted datetime and also a boolean value to indicate whether it
     * contains time or not.
     * @param dateTime The LocalDateTime involved.
     * @param hasTime True if the object contains time and false if it does not contain time.
     */
    public DukeDateTime(LocalDateTime dateTime, boolean hasTime) {
        this.dateTime = dateTime;
        this.hasTime = hasTime;
    }

    @Override
    public String toString() {
        return hasTime
                ? dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a"))
                : dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }
}
