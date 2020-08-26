// DatedTask.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

package ikura.task;

import java.util.List;
import java.util.Arrays;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import ikura.util.Pair;

import java.time.format.DateTimeParseException;
import ikura.util.InvalidInputException;

/**
 * A helper class with methods common to tasks with a date component (Event and Deadline).
 */
public class DatedTask {

    /**
     * Parses the provided input, extracting the task's description and the date. The input should
     * be of the form "<description> /<dateSpec> <date>".
     *
     * @param kind     the specific kind; either "deadline" or "event".
     * @param input    the user input.
     * @param dateSpec the keyword for specifying the date (after the slash); either 'at' or 'by'.
     * @param usage    the correct usage for the command (used for the error message).
     * @return a Pair of strings; first is the description, and second is the date.
     */
	public static Pair<String, String> parse(String kind, String input, String dateSpec, String usage)
        throws InvalidInputException {

        var slash = input.indexOf('/');

        if (slash == 0 || input.isEmpty()) {
            throw new InvalidInputException("task description cannot be empty", usage);

        } else if (slash == -1) {
            throw new InvalidInputException(String.format("%s requires a date", kind), usage);
        }

        var item = input.substring(0, slash).strip();
        var when = input.substring(slash + 1).strip();

        assert !item.isEmpty();

        if (!when.startsWith(dateSpec + " ")) {
            throw new InvalidInputException("incorrect date specification", usage);
        }

        when = when.substring(3).strip();
        return new Pair<>(item, when);
	}

    /**
     * Parses the input string as a date according to ISO-8601 format (yyyy-mm-dd).
     *
     * @param date the input string.
     * @return the LocalDate representing the input date.
     * @throws InvalidInputException if the input was not in the correct format.
     */
    public static LocalDate parseDate(String date) throws InvalidInputException {
        // TODO: handle more formats, eg dd/mm/yy, dd/mm/yyyy, dd/mm
        // TODO: handle offsets from now, eg. +7d or something like that

        try {
            return LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException(String.format("invalid date format: %s", e.getMessage()),
                "yyyy-mm-dd");
        }
    }
}
