package duke.tool;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Converts time to date and date to time.
 */
public class TimeConverter {
    /**
     * Outputs the integer representation of a datetime.
     * @param localDateTime selected date.
     * @return integer representation of the time.
     */
    public int convertTimeToInt(LocalDateTime localDateTime) {
        int hours = localDateTime.getHour();
        int minutes = localDateTime.getMinute();
        return hours * 60 + minutes;
    }

    /**
     * Outputs the local time representation of a integer.
     * @param minutes time in minutes.
     * @return the local time representation of a integer.
     */
    public LocalTime convertIntToTime(int minutes) {
        int hours = minutes / 60;
        int min = minutes % 60;
        return LocalTime.of(hours, min);
    }
}
