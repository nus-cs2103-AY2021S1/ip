package duke.time;

import java.time.LocalDateTime;

/**
 * Time stores time information. The information can either be a specific
 * time or some string description of the time.
 */
public class Time {

    public LocalDateTime time;
    public String timeDescription;

    private Time(LocalDateTime time) {
        this.time = time;
        timeDescription = null;
    }

    private Time(String timeDescription) {
        time = null;
        this.timeDescription = timeDescription;
    }

    /**
     * Converts a string to Time.
     * @param timeString the time string
     * @return the Time object formed
     */
    public static Time stringToTime(String timeString) {
        LocalDateTime time = TimeParser.parse(timeString);

        if (time == null) {
            return new Time(timeString);
        } else {
            return new Time(time);
        }
    }

    /**
     * Compares with another Time.
     * Only comparable when both Time are specific time.
     * @param time the time to compare to
     * @return the result of comparison
     */
    public TimeComparison compareTo(Time time) {
        if (time.time == null || this.time == null) {
            return TimeComparison.INCOMPARABLE;
        } else if (this.time.isBefore(time.time)) {
            return TimeComparison.IS_BEFORE;
        } else if (this.time.isAfter(time.time)) {
            return TimeComparison.IS_AFTER;
        } else if (this.time.isEqual(time.time)) {
            return TimeComparison.EQUAL;
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        if (time != null) {
            return time.format(TimeFormat.dateTimeFormatter);
        } else {
            return timeDescription;
        }
    }
}
