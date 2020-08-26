import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTime {
    private LocalDate date = LocalDate.now();
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final String INVALIDDATEMESSAGE = "Your Date must be in the format yyyy-mm-dd. Initialising date to Today!";

    public DateTime(String date) {
        if (isValidFormat(date)) {
            this.date = LocalDate.parse(date);
        } else {
            System.out.println(INVALIDDATEMESSAGE);
        }
    }

    public static Boolean isValidFormat(String date) {
        try {
            FORMATTER.parse(date);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public String saveString() {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String toString() {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}