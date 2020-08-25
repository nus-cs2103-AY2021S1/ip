package duck;

import duck.exception.DuckException;
import duck.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class Parser {
    private static final String[] dateSeparators = {"/at", "/by"};

    public static Option parseOption(String input) {
        String[] inputSplit = input.split("\\s+");
        Option option = Option.from(inputSplit[0]);
        return option;
    }

    public static String parseDescription(String input) throws DuckException {
        if (input.length() == 0) {
            throw new DuckException("The description field cannot be empty!");
        }

        for (String separator : Parser.dateSeparators) {
            if (input.contains(separator)) {
                input = input.substring(0, input.indexOf(separator));
            }
        }
        return input.strip();
    }

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
