import java.time.LocalDate;

public class DateParser {
    public static TaskDate parseDate(String dateString) {
        String[] dateAndTime = dateString.split(" ");
        String[] dates = dateAndTime[0].split("/");

        int year = Integer.valueOf(dates[0]);
        int month = Integer.valueOf(dates[1]);
        int day = Integer.valueOf(dates[2]);

        return new TaskDate(LocalDate.of(year, month, day), dateAndTime[1]);
    }

    public static TaskDate getRange(String dateString, boolean startOrEnd) {
        String[] dateAndTimes = dateString.split(" ");
        String[] dates = dateAndTimes[0].split("/");

        int year = Integer.valueOf(dates[0]);
        int month = Integer.valueOf(dates[1]);
        int day = Integer.valueOf(dates[2]);

        if (startOrEnd) {
            return new TaskDate(LocalDate.of(year, month, day), dateAndTimes[1]);
        } else {
            return new TaskDate(LocalDate.of(year, month, day), dateAndTimes[2]);
        }
    }
}
