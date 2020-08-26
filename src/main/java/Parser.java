import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Parser {
    public static Date parseDate(String strDate) throws DukeException{
        String formatWithMin = "y-M-d HH:mm";
        String formatWithoutMin = "y-M-d";
        try {
            return new SimpleDateFormat(strDate.length() > 11 ? formatWithMin : formatWithoutMin).parse(strDate);
        } catch (ParseException e) {
            throw new DukeException("Sorry! Wrong time format!");
        }
    }
}
