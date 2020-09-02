// TaskParser.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

package ikura.task;

import java.util.Optional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import ikura.util.Pair;

import java.time.format.DateTimeParseException;
import ikura.util.InvalidInputException;

/**
 * A helper class with methods to parse task descriptions.
 */
public class TaskParser {

    /**
     * Parses the provided input, extracting the task's description and the date. The input should
     * be of the form "(description) /(dateSpec) (date)".
     *
     * @param kind      the specific kind; eg. "deadline" or "event".
     * @param input     the user input.
     * @param dateSpec  the keyword for specifying the date (after the slash); either 'at' or 'by'. If
     *                  it is empty, then the task does not accept a date.
     * @param usage     the correct usage for the command (used for the error message).
     * @return a Pair of strings; first is the title, and second is the date.
     * @throws InvalidInputException when the input is malformed.
     */
    public static TaskDescription parse(String kind, String input, Optional<String> dateSpec, String usage)
        throws InvalidInputException {

        // TODO: make kind an enum

        var slash = input.indexOf('/');
        String when = "";

        if (slash == 0 || input.isEmpty()) {
            throw new InvalidInputException("task description cannot be empty", usage);

        } else if (slash == -1 && dateSpec.isPresent()) {
            throw new InvalidInputException(String.format("%s requires a date", kind), usage);
        }

        var title = input.substring(0, slash).strip();
        assert !title.isEmpty();


        // TODO: allow specifying both /at and /desc here
        if (dateSpec.isPresent()) {

            when = input.substring(slash + 1).strip();

            if (!when.startsWith(dateSpec.get() + " ")) {
                throw new InvalidInputException("incorrect date specification", usage);
            } else {
                when = when.substring(3).strip();
            }

            return TaskDescription.withTitleAndDate(title, parseDate(when));

        } else if (slash != -1) {
            when = input.substring(slash + 1).strip();
            if (when.startsWith("desc ")) {

                var desc = when.substring(4).strip();
                return TaskDescription.withTitleAndDescription(title, desc);

            } else {
                throw new InvalidInputException(String.format("Unexpected argument '%s'", when), usage);
            }
        } else {

            return TaskDescription.withTitle(title);
        }
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
