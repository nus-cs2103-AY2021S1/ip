import java.time.LocalDate;

import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

import java.util.Optional;
public class DateParser {
    public static Optional<LocalDate> parse(String dateStr) {
        String[] formats = {"yyyy-MM-dd", "dd-MM-yyyy", "dd MMM yyyy", "yyyy/MM/dd", "dd/MM/yyyy"};
        for (int i = 0; i < formats.length; i++) {
            try {
                LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(formats[i]));
                return Optional.of(date);
            } catch (DateTimeParseException e) {}
        }
        return Optional.empty();
    }
}
