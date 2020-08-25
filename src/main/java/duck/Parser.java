package duck;

import duck.exception.DuckException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Parser is a helper class that contains static methods for
 * parsing the various components from the user input.
 */
public class Parser {
    private static final String[] dateSeparators = {"/at", "/by"};

    /**
     * Returns the Option enumerator based on the first word separated
     * by whitespace.
     *
     * @param input Input from user.
     * @return Option enumerator.
     */
    public static Option parseOption(String input) {
        String[] inputSplit = input.split("\\s+");
        Option option = Option.from(inputSplit[0]);
        return option;
    }

    /**
     * Returns the description portion of the user input.
     * The first check is for the presence of any date and strips the date portion away.
     * The second check is for a valid description length.
     *
     * @param input Input from user.
     * @return Description portion of user input.
     * @throws DuckException If input is empty.
     */
    public static String parseDescription(String input) throws DuckException {
        input = input.strip();
        for (String separator : Parser.dateSeparators) {
            if (input.contains(separator)) {
                input = input.substring(0, input.indexOf(separator)).strip();
            }
        }

        if (input.length() == 0) {
            throw new DuckException("The description field cannot be empty!");
        }

        return input;
    }

    /**
     * Returns the date portion of the user input.
     * Strips away the input before the separators including the separator.
     * Checks for a valid date length before parsing with LocalDate.
     * If no valid date is obtained, an exception is thrown.
     *
     * @param input Input from user.
     * @return Date of LocalDate class.
     * @throws DuckException If date field is empty or not in the correct format.
     */
    public static LocalDate parseDate(String input) throws DuckException {
        String date = "";
        for (String separator : Parser.dateSeparators) {
            if (input.contains(separator)) {
                date = input.substring(input.indexOf(separator) + separator.length()).strip();
            }
        }

        if (date.length() < 1) {
            throw new DuckException("Please specify a date");
        }
        try {
            LocalDate parsedDate = LocalDate.parse(date);
            return parsedDate;
        } catch (DateTimeParseException e) {
            throw new DuckException("Date format not supported. Use \"yyyy-mm-dd\", E.g. 2020-02-02");
        }
    }

    /**
     * Returns the task number portion of the user input.
     * Task number should be the second portion delimited by whitespace.
     * A valid input with a task number should only have 2 words, the option and task number.
     *
     * @param input Input from user.
     * @return Task number of type int
     * @throws DuckException If input is invalid or task number portion is not a number.
     */
    public static int parseTaskNumber(String input) throws DuckException {
        String[] inputSplit = input.split("\\s+");
        if (inputSplit.length != 2) {
            throw new DuckException("Please provide a task number!");
        }

        try {
            int taskNumber = Integer.parseInt(inputSplit[1]);
            return taskNumber;
        } catch (NumberFormatException e) {
            throw new DuckException("Invalid number provided");
        }
    }

}
