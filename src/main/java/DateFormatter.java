import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DateFormatter {
    // @@author aizatazhar-reused
    // Reused from https://stackoverflow.com/a/16990333/12003017 with minor modifications
    private static List<String> dateFormats = Arrays.asList(
            "yyyy-MM-dd hhmm",
            "yyyy-MM-dd"
    );
    
    public static Date extractTimestampInput(String strDate) throws DukeException {
        for (String format: dateFormats) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            try {
                return sdf.parse(strDate);
            } catch (ParseException e) {
                //intentionally empty
            }
        }

        throw new DukeException("Invalid input for date. Given '"+strDate+"', "
                + "expecting format yyyy-MM-dd hhmm or yyyy-MM-dd.");
    }
    // @@author
    
    public static String formatDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm");
        return dateFormat.format(date);
    }
}
