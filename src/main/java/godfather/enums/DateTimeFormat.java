package godfather.enums;

import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * An Enumeration of the various DateTime formatters that Duke shall use to parse the Date and Time from user commands
 */
public enum DateTimeFormat {
    // Add Formats for date and time here as needed...
    DATE_OUTPUT_FORMATTER1("MMM/d/yyyy"), DATE_INPUT_FORMATTER1("d/M/yyyy"), DATE_INPUT_FORMATTER2("yyyy/M/d"),
    TIME_FORMATTER("HHmm"), INVALID;
    private DateTimeFormatter formatter;
    DateTimeFormat() {
    }
    DateTimeFormat(String format) {
        this.formatter = DateTimeFormatter.ofPattern(format).withResolverStyle(ResolverStyle.SMART);
    }
    public DateTimeFormatter getFormat() {
        return this.formatter;
    }
    /**
     * Returns a stream of all the Formatters, that can be filtered through to select the correct formatter for the
     * user's input
     *
     * @return Stream of DateTimeFormatter objects
     */
    public static Stream<DateTimeFormatter> getFormatterStream() {
        return Arrays.stream(DateTimeFormat.values()).map(DateTimeFormat::getFormat);
    }
}
