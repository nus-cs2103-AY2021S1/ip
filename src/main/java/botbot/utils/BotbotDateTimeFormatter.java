package botbot.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a DateTime formatter for Botbot.
 */
public class BotbotDateTimeFormatter {
    private static final String FORMAT_DATE = "d MMM yyyy";
    private static final String FORMAT_DATE_HOUR = FORMAT_DATE + " ha";
    private static final String FORMAT_DATE_HOUR_MINUTES = FORMAT_DATE + " h.mma";

    private static final double NO_TIME_FLAG = Math.PI * Math.pow(10, 13);
    private static final int NO_TIME_FLAG_HOUR = 3;
    private static final int NO_TIME_FLAG_MINUTE = 14;
    private static final int NO_TIME_FLAG_SECOND = 15;
    private static final int NO_TIME_FLAG_NANO = 926535898;
    public static final String NO_TIME_FLAG_STR = String.format(" 0%.0f", NO_TIME_FLAG);

    /**
     * Converts a LocalDateTime object to a string that will be shown to the user.
     *
     * @param dateTime LocalDateTime object to be converted.
     * @return Converted string.
     */
    public static String convertDateTimeToStr(LocalDateTime dateTime) {
        String format;
        if ((dateTime.getHour() == NO_TIME_FLAG_HOUR) && (dateTime.getMinute() == NO_TIME_FLAG_MINUTE)
                && (dateTime.getSecond() == NO_TIME_FLAG_SECOND)
                && (dateTime.getNano() == NO_TIME_FLAG_NANO)) {
            format = FORMAT_DATE;
        } else if (dateTime.getMinute() == 0) {
            format = FORMAT_DATE_HOUR;
        } else {
            format = FORMAT_DATE_HOUR_MINUTES;
        }
        return dateTime.format(DateTimeFormatter.ofPattern(format));
    }
}
