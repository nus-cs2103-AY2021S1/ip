package utility;

import java.time.LocalDate;

public class DateParser {

    /**
     * Converts a date from "yyyy-MM-dd" to "Month dd yyyy"
     * @param date original date representation
     * @return new date representation
     */
    public static String format(LocalDate date) {
        return date.getMonth().toString() + " "
                + date.getDayOfMonth() + " " + date.getYear();
    }
}
