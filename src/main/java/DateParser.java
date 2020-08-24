import java.time.LocalDate;

public class DateParser {

    public static String format(LocalDate date) {
        return date.getMonth().toString() + " "
                + date.getDayOfMonth() + " " + date.getYear();
    }
}
