package duke.time;

import duke.exception.DukeInputException;

/**
 * Representation for a point in time in text.
 */
public class TimePoint {

    /**
     * String information of time represented by this <code>TimePoint</code>.
     */
    private String timePoint;

    /**
     * Creates an empty <code>TimePoint</code>.
     */
    public TimePoint() {
        this.timePoint = "";
    }

    private TimePoint(String timePoint) {
        this.timePoint = timePoint;
    }

    /**
     * Factory method for creating <code>TimePoint</code>s from a given string input.
     * Attempts to create a <code>DateTime</code> representation of the time description provided.
     * If unable to parse provided description, a String-based representation is created instead.
     *
     * @param timeInfoString Time representation input to be parsed.
     * @return <code>TimePoint</code> object representing inputted time.
     */
    public static TimePoint parseTime(String timeInfoString) {

        try {
            // Attempt to parse information as date and time
            return DateTimeParser.parse(timeInfoString);

        } catch (DukeInputException e) {
            // If failed, return a string-based representation of a timing instead
            return new TimePoint(timeInfoString);
        }
    }

    /**
     * Returns string representation of this <code>TimePoint</code> for display.
     *
     * @return String representation of point in time.
     */
    public String toString() {
        return this.timePoint;
    }

    /**
     * Returns formatted string for saving into save file.
     *
     * @return Formatted string.
     */
    public String toSaveString() {
        return this.timePoint;
    }
}
