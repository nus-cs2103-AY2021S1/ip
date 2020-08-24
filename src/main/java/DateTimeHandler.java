import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeHandler {

    public static LocalDateTime parseDateTime(String dateTime) {
        try {
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("dd-mm-yy HHmm"));
        } catch (IllegalArgumentException e) {
            throw new DukeException(dateTime + "is an invalid datetime format! Please use dd-mm-yy HHmm (24 hour)");
        }
    }



}
