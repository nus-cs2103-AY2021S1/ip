import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Parser {
    private static final String[] dateFormats = new String[] {
            "d/M/y HHmm", "d-M-y HHmm", "d/M/y", "d-M-y"
    };

    public static LocalDateTime parseDateTime(String dateTime) {
        DateFormat df = new SimpleDateFormat("MMM d yyyy HHmm'H'");
        for (String format : dateFormats) {
            try {
                Date d = new SimpleDateFormat(format).parse(dateTime);
                return d.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            } catch (ParseException ignored) {}
        }
        return null;
    }
}
