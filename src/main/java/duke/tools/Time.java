package duke.tools;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Converts the time string
 * of format "yyyy-mm-dd" into "MMM_d_yyyy".
 */
public class Time {
    private LocalDate localDate;

    /**
     * Constructs a Time.
     *
     * @param time A string of format
     *             of "yyyy-mm-dd".
     */
    public Time(String time) {
        localDate = LocalDate.parse(time);
    }

    @Override
    public String toString() {
        return localDate.format(DateTimeFormatter.ofPattern("MMM_d_yyyy"));
    }
}
