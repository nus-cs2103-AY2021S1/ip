package luoyi.duke.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Encapsulate parsing of time string to get LocalDate or LocalTime.
 */
public class TimeParser {
    // Accept YYYYMMDD, DDMMYYYY
    private static final List<Pattern> DATE_PATTERN_LIST = new ArrayList<>(
            List.of(Pattern.compile("(?<year>\\d{4})[\\s\\-.]?(?<month>\\d{1,2})[\\s\\-.]?(?<day>\\d{1,2})"),
                    Pattern.compile("(?<day>\\d{1,2})[\\s\\-.]?(?<month>\\d{1,2})[\\s\\-.]?(?<year>\\d{4})")));
    // Accept YYYY MM DD HHMM, DD MM YYYY HHMM
    private static final List<Pattern> DATETIME_PATTERN_LIST = new ArrayList<>(
            List.of(Pattern.compile("(?<year>\\d{4})[\\s\\-.](?<month>\\d{1,2})[\\s\\-.](?<day>\\d{1,2})"
                    + "[\\s\\-.T](?<hour>\\d{1,2})[\\s\\-.:]?(?<minute>\\d{2})"),
                    Pattern.compile("(?<day>\\d{1,2})[\\s\\-.](?<month>\\d{1,2})[\\s\\-.](?<year>\\d{4})"
                            + "[\\s\\-.T](?<hour>\\d{1,2})[\\s\\-.:]?(?<minute>\\d{2})")));

    /**
     * Returns a parsed date.
     *
     * @param string Time as a string.
     * @return LocalDate representing the time string.
     */
    public static LocalDate parseDate(String string) {
        if (!isDate(string)) {
            throw new RuntimeException(string +" does not have a date time!");
        }

        Pattern pattern = DATE_PATTERN_LIST
                .stream()
                .filter(x -> x.matcher(string).find())
                .findFirst()
                .orElseThrow();
        Matcher matcher = pattern.matcher(string);
        if (!matcher.matches()) {
            throw new RuntimeException(string +" failed to match regex!");
        }
        return LocalDate.of(Integer.parseInt(matcher.group("year")),
                Integer.parseInt(matcher.group("month")),
                Integer.parseInt(matcher.group("day")));
    }

    /**
     * Returns a parsed date time.
     *
     * @param string Time as a string.
     * @return LocalDateTime representing the time string.
     */
    public static LocalDateTime parseDateTime(String string) {
        if (!isDateTime(string)) {
            throw new RuntimeException(string +" does not have a date time!");
        }

        Pattern pattern = DATETIME_PATTERN_LIST
                .stream()
                .filter(x -> x.matcher(string).find())
                .findFirst()
                .orElseThrow();
        Matcher matcher = pattern.matcher(string);
        if (!matcher.matches()) {
            throw new RuntimeException(string +" failed to match regex!");
        }
        return LocalDateTime.of(Integer.parseInt(matcher.group("year")),
                Integer.parseInt(matcher.group("month")),
                Integer.parseInt(matcher.group("day")),
                Integer.parseInt(matcher.group("hour")),
                Integer.parseInt(matcher.group("minute")));
    }

    /**
     * Checks if a string is a date time.
     *
     * @param string String to be checked.
     * @return True if the string is a date time, false otherwise.
     */
    public static boolean isDateTime(String string) {
        return DATETIME_PATTERN_LIST.stream().anyMatch(x -> x.matcher(string).matches());
    }

    /**
     * Checks if a string is a date.
     *
     * @param string String to be checked.
     * @return True if the string is a date, false otherwise.
     */
    public static boolean isDate(String string) {
        return DATE_PATTERN_LIST.stream().anyMatch(x -> x.matcher(string).matches());
    }
}
