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
            return DateTimeParser.parse(timeInfoString);
        } catch (DukeInputException e) {
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
