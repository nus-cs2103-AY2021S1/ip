package duke.time;

import java.time.LocalDateTime;

/**
 * Time stores time information. The information can either be a specific
 * time or some string description of the time.
 */
public class Time {

    private LocalDateTime time;
    private String timeDescription;

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
        assert !timeString.isBlank() : "timeString cannot be empty";

        LocalDateTime time = TimeParser.parse(timeString);

        if (time == null) {
            return new Time(timeString);
        } else {
            return new Time(time);
        }
    }

    @Override
    public String toString() {
        if (time != null) {
            return time.format(TimeFormat.DATE_TIME_FORMATTER);
        } else {
            return timeDescription;
        }
    }

    public LocalDateTime getTime() {
        return time;
    }
}
