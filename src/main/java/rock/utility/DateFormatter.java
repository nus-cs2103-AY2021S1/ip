package rock.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Format date.
 */
public class DateFormatter {
    /**
     * Format data of form [yyyy-mm-dd] to the form [MMM d yyyy]
     * @param str unformatted date
     * @return formatted date
     */
    public static String formatDate(String str) {
        LocalDate d;
        try {
            d = LocalDate.parse(str);
        } catch (Exception e) {
            return str;
        }
        return d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
