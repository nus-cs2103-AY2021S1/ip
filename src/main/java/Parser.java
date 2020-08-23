import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {

    public static String[] splitCommandAndDescription(String string) {
        return string.split(" ", 2);
    }

    public static String[] splitDeadlineTime(String string) {
        return string.split("/by ", 2);
    }

    public static String[] splitEventTime(String string) {
        return string.split("/at ", 2);
    }

    public static LocalDate stringToDate(String string) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate myDateObj = LocalDate.parse(string, formatter);
        return myDateObj;
    }

    public static String dateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
