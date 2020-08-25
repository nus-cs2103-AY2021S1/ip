package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TimeParser {
        public static String parseTime(String str) {
            LocalDate date = LocalDate.parse(str);
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }

        public static boolean isValidTime(String str) {
            String[] arr = str.split("-", 3);
            if (arr.length == 3) {
                int year = Integer.parseInt(arr[0]);
                int month = Integer.parseInt(arr[1]);
                int date = Integer.parseInt(arr[2]);
                return month <= 12 && date <= 31;
            } else {
                return false;
            }
        }
}
