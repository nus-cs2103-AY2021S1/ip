import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeParsing {
    static LocalDate parseDate(String date) {
        return LocalDate.parse(date);
    }

    static String parse24HTime(String time) {
        String result = LocalTime
            .parse(time, DateTimeFormatter.ofPattern("HH:mm"))
            .format(DateTimeFormatter.ofPattern("hh:mm a"));

        return result;
    }
}
