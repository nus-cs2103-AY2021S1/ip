import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Arrays;
import java.util.stream.Stream;

public enum DateTimeFormat {
    // Add Formats for date and time here as needed...
    DATE_OUTPUT_FORMATTER1("MMM dd yyy"),
    DATE_INPUT_FORMATTER1("d/M/yyyy"),
    DATE_INPUT_FORMATTER2("yyyy/M/d"),
    TIME_FORMATTER("HHmm"),
    INVALID;
    
    
    private DateTimeFormatter formatter;
    
    DateTimeFormat() {
    }
    
    DateTimeFormat(String format) {
        this.formatter = DateTimeFormatter.ofPattern(format).withResolverStyle(ResolverStyle.SMART);
    }
    
    public DateTimeFormatter getFormat() {
        return this.formatter;
    }
    
    public static Stream<DateTimeFormatter> getFormatterStream() {
        return Arrays.stream(DateTimeFormat.values()).map(DateTimeFormat::getFormat);
    }
    
}
