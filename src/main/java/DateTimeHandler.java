import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeHandler {

    public static final String STANDARD_DATETIME_FORMAT_STRING = "dd-MM-uuuu HHmm";
    public static final String STANDARD_2400_FORMAT_STRING = "HHmm";
    public static final DateTimeFormatter STANDARD_DATETIME_FORMAT = DateTimeFormatter.ofPattern(
            STANDARD_DATETIME_FORMAT_STRING);
    public static final DateTimeFormatter STANDARD_2400_FORMAT = DateTimeFormatter.ofPattern(
            STANDARD_2400_FORMAT_STRING);

    public static LocalDateTime parseDateTime(String dateTime) {
        try {
            return LocalDateTime.parse(dateTime, STANDARD_DATETIME_FORMAT);
        } catch (DateTimeParseException e) {
            throw new DukeException(dateTime + "is an invalid datetime format! Please use "
                    + STANDARD_DATETIME_FORMAT_STRING + " (24hr)");
        }
    }

    public static Pair<LocalDateTime, LocalDateTime> parseEventTimings(String eventTiming) {
        int acceptableLength1 = STANDARD_DATETIME_FORMAT_STRING.length() + 1 + STANDARD_2400_FORMAT_STRING.length();
        int acceptableLength2 = STANDARD_DATETIME_FORMAT_STRING.length() * 2 + 1;
        if (eventTiming.length() != acceptableLength1 && eventTiming.length() != acceptableLength2) {
            throw new DukeException(eventTiming + " is not a valid event timing. Please use either\n"
                    + STANDARD_DATETIME_FORMAT_STRING + "-" + STANDARD_DATETIME_FORMAT_STRING + " (24hr)\n"
                    + STANDARD_DATETIME_FORMAT_STRING + "-" + STANDARD_2400_FORMAT_STRING + " (24hr)");
        }
        String firstTiming = eventTiming.substring(0, STANDARD_DATETIME_FORMAT_STRING.length());
        String secondTiming = eventTiming.substring(STANDARD_DATETIME_FORMAT_STRING.length() + 1);
        try {
            LocalDateTime dateTime1 = LocalDateTime.parse(firstTiming, STANDARD_DATETIME_FORMAT);
            LocalDateTime dateTime2;
            if (secondTiming.length() == STANDARD_DATETIME_FORMAT_STRING.length()) {
                dateTime2 = LocalDateTime.parse(secondTiming, STANDARD_DATETIME_FORMAT);
            } else {
                LocalTime time = LocalTime.parse(secondTiming, STANDARD_2400_FORMAT);
                dateTime2 = LocalDateTime.of(dateTime1.toLocalDate(), time);
            }

            if (dateTime2.compareTo(dateTime1) < 0) {
                throw new DukeException("End timing must be later than start timing!");
            }
            return Pair.of(dateTime1, dateTime2);

        } catch (DateTimeParseException e) {
            throw new DukeException(eventTiming + " is not a valid event timing. Please use either\n"
                    + STANDARD_DATETIME_FORMAT_STRING + "-" + STANDARD_DATETIME_FORMAT_STRING + " (24hr)\n"
                    + STANDARD_DATETIME_FORMAT_STRING + "-" + STANDARD_2400_FORMAT_STRING + " (24hr)");

        }
    }

    public static void main(String[] args) {
        System.out.println(parseDateTime("24-09-2000 1800"));
        System.out.println(parseEventTimings("24-09-2000 1800-2000"));
        System.out.println(parseEventTimings("24-09-2000 1800-26-09-2000 2000"));
        System.out.println(parseEventTimings("24-09-2000 180026-09-2000 2000"));

    }



}
