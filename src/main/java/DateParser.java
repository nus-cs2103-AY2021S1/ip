import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateParser {
    public static TaskDate parseDate(String dateString) {
        LocalDateTime dateTime = LocalDateTime.parse(dateString,
                                            DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"));

//        String[] dateAndTime = dateString.split(" ");
//        String[] dates = dateAndTime[0].split("/");
//
//        int year = Integer.valueOf(dates[0]);
//        int month = Integer.valueOf(dates[1]);
//        int day = Integer.valueOf(dates[2]);

        return new TaskDate(dateTime);

//        return new TaskDate(LocalDate.of(year, month, day), dateAndTime[1]);
    }

    public static TaskDate getRange(String dateString, boolean startOrEnd) {
        String[] dateAndTimes = dateString.split(" ");
        String[] dates = dateAndTimes[0].split("/");

        int year = Integer.valueOf(dates[0]);
        int month = Integer.valueOf(dates[1]);
        int day = Integer.valueOf(dates[2]);

        if (startOrEnd) {
            return new TaskDate(LocalDate.of(year, month, day)
                                .atTime(LocalTime.parse(dateAndTimes[1],
                                                        DateTimeFormatter.ofPattern("HHmm"))));
        } else {
            return new TaskDate(LocalDate.of(year, month, day)
                                .atTime(LocalTime.parse(dateAndTimes[2],
                                        DateTimeFormatter.ofPattern("HHmm"))));
        }
    }

    public static TaskDate parseRangeFromStorage(String storedString, boolean startOrEnd) {
        String[] dateAndTime = storedString.split(" - ");;

        if (startOrEnd) {
            return new TaskDate(LocalDateTime.parse(dateAndTime[0],
                                DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")));
        } else {
            return new TaskDate(LocalDate.parse(dateAndTime[0],
                                                DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"))

                                .atTime(LocalTime.parse(dateAndTime[1],
                                                        DateTimeFormatter.ofPattern("HHmm"))));
        }
    }

    public static TaskDate parseDateFromStorage(String storedString) {
        String[] dateAndTime = storedString.split(" ");

        LocalDateTime dateTime = LocalDateTime.parse(storedString,
                                                     DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));

        return new TaskDate(dateTime);
    }
}