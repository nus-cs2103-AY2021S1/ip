package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class to store methods for parsing
 */
public class Parser {
    private static final String[] dateFormats = new String[]{"yyyy-MM-dd", "dd-MM-yyyy", "yyyy/MM/dd", "dd/MM/yyyy",
        "MMM d yyyy", "d MMM yyyy"};

    /**
     * @param input the date to be parsed
     * @return a parsed date
     * @throws IllegalArgumentException exception if the date cannot be parsed to one of the pre-specified format
     */
    public static LocalDate parseDate(String input) throws IllegalArgumentException {
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
