package time;

import java.time.format.DateTimeFormatter;

public class TimeFormat {

    public static final DateTimeFormatter dateFormatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static final DateTimeFormatter timeFormatter1 =
            DateTimeFormatter.ofPattern("HH:mm:ss");

    public static final DateTimeFormatter timeFormatter2 =
            DateTimeFormatter.ofPattern("HH:mm");

    public static final DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
}
