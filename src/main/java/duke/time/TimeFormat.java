package duke.time;

import java.time.format.DateTimeFormatter;

/**
 * The accepted formats for parsing a string to some specific time.
 */
public class TimeFormat {

    public static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static final DateTimeFormatter TIME_FORMATTER_1 =
            DateTimeFormatter.ofPattern("HH:mm:ss");

    public static final DateTimeFormatter TIME_FORMATTER_2 =
            DateTimeFormatter.ofPattern("HH:mm");

    public static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
}
