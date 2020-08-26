package duke.time;

import duke.exception.DukeInputException;

public class TimePoint {

    private String timePoint;

    public TimePoint() {
        this.timePoint = "";
    }

    private TimePoint(String timePoint) {
        this.timePoint = timePoint;
    }

    public static TimePoint of(String timeInfoString) {

        try {
            // Attempt to parse information as date and time
            return DateTimeParser.parse(timeInfoString);

        } catch (DukeInputException e) {
            // If failed, return a string-based representation of a timing instead
            System.out.println(e.getMessage());
            return new TimePoint(timeInfoString);
        }
    }

    public String toString() {
        return this.timePoint;
    }

    public String toSaveString() {
        return this.timePoint;
    }
}
