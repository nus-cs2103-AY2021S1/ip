package Duke.Main;

import Duke.Errors.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    /**
     * Converts a date string into LocalDateTime object
     * @param by
     * @return LocalDateTime object
     */
    public static LocalDateTime strToDate(String by) {
        String[] arr = by.split("\\s+");
        LocalDate localDate;
        LocalDateTime localDateTime;
        try {
            if (arr.length == 1) {
                // Only date provided
                String date = arr[0].replace("/", "-");
                localDate = LocalDate.parse(date);
                localDateTime = localDate.atTime(0, 0);
            } else if (arr.length == 2 && arr[1].length() == 4) {
                // Date and time provided
                String date = arr[0].replace("/", "-");
                localDate = LocalDate.parse(date);
                String time = arr[1];
                int hours = Integer.parseInt(time.substring(0, 2));
                int minutes = Integer.parseInt(time.substring(2, 4));

                localDateTime = localDate.atTime(hours, minutes);
            } else {
                throw new InvalidCommandException();
            }

            return localDateTime;
        } catch (Exception e) {
            Ui.echo("☹ OOPS!!! Please enter date format as yyyy-mm-dd hhmm, e.g. 2019-12-01 1800");
            return null;
        }
    }

    /**
     * Parses a LocalDateTime object into a String for printing on Duke
     * @param datetime LocalDateTime object
     * @return Parsed String
     */
    public static String parseDateTime(LocalDateTime datetime) {
        return datetime.format(DateTimeFormatter.ofPattern("MMM d yyyy, h.mma"));
    }
}