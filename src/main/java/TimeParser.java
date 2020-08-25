import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
// import java.time.temporal.ChronoUnit;

public class TimeParser {
        public static String parseTime(String str) {
            LocalDate date = LocalDate.parse(str);
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }

        public static boolean isValidTime(String str) {
            String[] arr = str.split("-", 3);
//            System.out.println(Arrays.toString(arr));
            if (arr.length == 3) {
                int year = Integer.parseInt(arr[0]);
                int month = Integer.parseInt(arr[1]);
                int date = Integer.parseInt(arr[2]);
                return month <= 12 && date <= 31;
            } else {
                return false;
            }
        }


//
//            //create dates from strings
//            LocalDate d1 = LocalDate.parse("2019-12-01");
//            LocalDate d2 = LocalDate.parse("2019-12-02");
//            LocalDate d3 = LocalDate.parse("2019-12-02");
//
//            //compare dates
//            System.out.println(d1.isBefore(d2)); // -> true
//            System.out.println(d1.isAfter(d2)); // -> false
//            System.out.println(d2.equals(d3)); // -> true
//
//            //work with dates
//            System.out.println(d1.getDayOfWeek()); // -> SUNDAY
//            System.out.println(d1.getMonth()); // -> DECEMBER
//            System.out.println(d1.plus(1, ChronoUnit.YEARS));  // -> 2020-12-01
//
//            // get today's date and print it in a specific format
//            LocalDate d4 = LocalDate.now();
//            System.out.println(d4); // -> 2019-10-15
//            System.out.println(d4.format(DateTimeFormatter.ofPattern("MMM d yyyy"))); // -> Oct 15 2019
}
