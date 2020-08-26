package time;

import java.time.LocalDateTime;

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

    public static Time stringToTime(String timeString) {
        LocalDateTime time = TimeParser.parse(timeString);

        if (time == null) {
            return new Time(timeString);
        } else {
            return new Time(time);
        }
    }

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
