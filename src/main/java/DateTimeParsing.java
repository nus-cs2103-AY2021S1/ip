import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeParsing {
    static LocalDate parseDate(String date) {
        return LocalDate.parse(date);
    }

    static String localDateToFormattedString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    static String localDateToString(LocalDate date) {
        int day = date.getDayOfMonth();
        String dayStr = day < 10 ? "0" + day : Integer.toString(day);
        int month = date.getMonthValue();
        String monthStr = month < 10 ? "0" + month : Integer.toString(month);
        int year = date.getYear();

        return year + "-" + monthStr + "-" + dayStr;
    }

    static String to12HTimeFormat(String time) {
        return LocalTime
            .parse(time, DateTimeFormatter.ofPattern("HH:mm"))
            .format(DateTimeFormatter.ofPattern("hh:mm a"));
    }

    static String to24HTimeFormat(String time) {
        return LocalTime
            .parse(time, DateTimeFormatter.ofPattern("hh:mm a"))
            .format(DateTimeFormatter.ofPattern("HH:mm"));
    }
}
