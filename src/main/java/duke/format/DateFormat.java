package duke.format;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import duke.exception.DateException;

public class DateFormat {
    private static final List<String> DATEFORMATS = Arrays.asList("d/M/yy HHmm", "d-M-yy HHmm",
            "d/M/yy h:mma", "d-M-yy h:mma", "d/M/yy", "d-M-yy");

    /**
     * Parses a date string and returns a Date object.
     * @param dateString Date in string format
     * @return A Date object that represents the date string
     * @throws DateException If date provided is of the wrong format.
     */
    public static Date parseDate(String dateString) throws DateException {
        for (String dateFormat : DATEFORMATS) {
            try {
                return new SimpleDateFormat(dateFormat).parse(dateString);
            } catch (ParseException e) {
                continue;
            }
        }
        throw new DateException("Invalid date format");
    }

    public static String formatDate(Date date) {
        return new SimpleDateFormat("d/M/yyyy HHmm").format(date);
    }
}
