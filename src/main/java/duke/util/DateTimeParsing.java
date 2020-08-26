package duke.util;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalTime;

public class DateTimeParsing {
    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date);
    }

    public static String localDateToFormattedString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public static String localDateToString(LocalDate date) {
        int day = date.getDayOfMonth();
        String dayStr = day < 10 ? "0" + day : Integer.toString(day);
        int month = date.getMonthValue();
        String monthStr = month < 10 ? "0" + month : Integer.toString(month);
        int year = date.getYear();

        return year + "-" + monthStr + "-" + dayStr;
    }

    public static String to12HTimeFormat(String time) {
        return LocalTime
            .parse(time, DateTimeFormatter.ofPattern("HH:mm"))
            .format(DateTimeFormatter.ofPattern("hh:mm a"));
    }

    public static String to24HTimeFormat(String time) {
        return LocalTime
            .parse(time, DateTimeFormatter.ofPattern("hh:mm a"))
            .format(DateTimeFormatter.ofPattern("HH:mm"));
    }
}
