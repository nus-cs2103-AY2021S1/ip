package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    static String[] dateFormats = new String[]{"yyyy-MM-dd", "dd-MM-yyyy", "yyyy/MM/dd", "dd/MM/yyyy", "MMM d yyyy",
        "d MMM yyyy"};

    static public LocalDate parseDate(String input) {
        for (String it : dateFormats) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(it);
                return LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e) {

            }
        }
        throw new IllegalArgumentException("☹ OOPS!!! I can't recognize the date provided");
    }
}
