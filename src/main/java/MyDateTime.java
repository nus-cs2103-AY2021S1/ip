import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class MyDateTime {
    public static LocalDateTime parse(String string) throws DateTimeParseException {
        try {
            return LocalDateTime.parse(string, DateTimeFormatter.ofPattern(" dd/MM/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            throw e;
        }
    }

    public static LocalDateTime load(String string) throws DateTimeParseException {
        try {
            return LocalDateTime.parse(string);
        } catch (DateTimeParseException e) {
            throw e;
        }
    }
}
