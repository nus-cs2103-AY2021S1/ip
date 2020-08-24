package timeformatter;

import java.time.LocalDate;

public class TimeFormatter {

    public static LocalDate localDate(String by) {
        String date = by.trim().replaceAll("/", "-");
        LocalDate localDate = LocalDate.parse(date);
        return localDate;
    }

    public static String prettyDate(LocalDate localDate) {
        return localDate.getDayOfWeek() + "," +localDate.getMonth().name().substring(0, 3) +
                " " + localDate.getDayOfMonth() + " " + localDate.getYear();
    }
}
