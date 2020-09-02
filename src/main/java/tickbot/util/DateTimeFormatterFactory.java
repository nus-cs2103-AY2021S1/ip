package tickbot.util;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class DateTimeFormatterFactory {
    private DateTimeFormatterFactory() { } // not meant to be initialized

    /**
     * Gets a {@code DateTimeFormatter} for inputs.
     * @return the {@code DateTimeFormatter} needed.
     */
    public static DateTimeFormatter getInputFormatter() {
        return new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd[ HH:mm[:ss]]")
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
            .toFormatter();
    }

    /**
     * Gets a {@code DateTimeFormatter} for outputs.
     * @return the {@code DateTimeFormatter} needed.
     */
    public static DateTimeFormatter getOutputFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }
}
