import java.time.LocalDateTime;

public class Parser {

    public static LocalDateTime toDateTime(String dateTime) throws GelException {
        String[] dateTimeSplit = dateTime.split(" ");
        if (dateTimeSplit.length != 2) {
            throw new GelException("    Your datetime has an invalid format... please use"
                    + " the format:YYYY-MM-DD HHMM");
        }
        String[] date = dateTimeSplit[0].split("-");
        if (date.length != 3) {
            throw new GelException("    Your date has an invalid format... please use"
                    + " the format:YYYY-MM-DD");
        }
        String time = dateTimeSplit[1];
        if (time.length() != 4) {
            throw new GelException("    Your time has an invalid format... please use"
                    + " the format:HHMM");
        }
        try {
            int year = Integer.parseInt(date[0]);
            int month = Integer.parseInt(date[1]);
            int dayOfMonth = Integer.parseInt(date[2]);
            int hour = Integer.parseInt(time.substring(0, 2));
            int minute = Integer.parseInt(time.substring(2));
            return LocalDateTime.of(year, month, dayOfMonth, hour, minute);
        } catch (Exception e){
            throw new GelException("    Please make sure that your date and time"
                    + "are numbers within the calendar and 24 hour clock");
        }
    }
}
