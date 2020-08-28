package duke.parser;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalTime;

// Class that deals with any parsing or conversion of date and time.
public class DateTimeParsing {
    /**
     * Parses a formatted date string (YYYY-MM-DD) and returns the LocalDate equivalent of the date.
     *
     * @param date String formatted date string
     * @return LocalDate LocalDate object of the provided date
     */
    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date);
    }

    /**
     * Converts the provided LocalDate object into a formatted string(MMM d yyyy).
     *
     * @param date LocalDate the date to convert into a formatted string
     * @return String formatted string of the provided date
     */
    public static String localDateToFormattedString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Converts the provided LocalDate object into a string(YYYY-MM-DD).
     *
     * @param date LocalDate the date to convert into a string
     * @return String string representation of the provided date
     */
    public static String localDateToString(LocalDate date) {
        int day = date.getDayOfMonth();
        String dayStr = day < 10 ? "0" + day : Integer.toString(day);
        int month = date.getMonthValue();
        String monthStr = month < 10 ? "0" + month : Integer.toString(month);
        int year = date.getYear();

        return year + "-" + monthStr + "-" + dayStr;
    }

    /**
     * Converts a 24-hr time format(HH:mm) into a 12-hr time format(hh:mm a).
     *
     * @param time String 24-hr time format(HH:mm)
     * @return String 12-hr time format(hh:mm a)
     */
    public static String to12HTimeFormat(String time) {
        return LocalTime
                .parse(time, DateTimeFormatter.ofPattern("HH:mm"))
                .format(DateTimeFormatter.ofPattern("hh:mm a"));
    }

    /**
     * Converts a 24-hr time format(HH:mm) into a 12-hr time format(hh:mm a).
     *
     * @param time String 12-hr time format(hh:mm a)
     * @return String 24-hr time format(HH:mm)
     */
    public static String to24HTimeFormat(String time) {
        return LocalTime
                .parse(time, DateTimeFormatter.ofPattern("hh:mm a"))
                .format(DateTimeFormatter.ofPattern("HH:mm"));
    }
}
