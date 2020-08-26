import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DateFormat {
    public static List<String> dateFormats = Arrays.asList("d/M/yy HHmm", "d-M-yy HHmm",
            "d/M/yy h:mma", "d-M-yy h:mma", "d/M/yy", "d-M-yy");

    public static Date parseDate(String dateString) throws DateException {
        for (String dateFormat : dateFormats) {
            try {
                return new SimpleDateFormat(dateFormat).parse(dateString);
            } catch (ParseException e) {

            }
        }
        throw new DateException("Invalid date format");
    }

    public static String formatDate(Date date) {
        return new SimpleDateFormat("d/M/yyyy HHmm").format(date);
    }
}
