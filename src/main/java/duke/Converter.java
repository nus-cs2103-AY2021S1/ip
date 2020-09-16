package duke;

import duke_exceptions.IllegalTaskTypeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * Represents methods to manipulate with formats, e.g. dates
 */

public class Converter {
    /**
     * Returns a formatted string transforming from "/at" to ":(at)".
     * Assume the input string must contain "/at"
     *
     * @param s input string
     * @return a formatted string
     */
    static String at(String s) {
        String first = s.split("/at ")[0];
        String second = s.split("/at ")[1];
        LocalDate date = LocalDate.parse(second);
        return first + "(at: " + date.format(DateTimeFormatter
                .ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns a formatted string transforming from "/by" to ":(by)".
     * Assume the input string must contain "/by".
     *
     * @param s input string
     * @return a formatted string
     */
    public static String by(String s) {
        String first = s.split("/by ")[0];
        String second = s.split("/by ")[1];
        LocalDate date = LocalDate.parse(second);
        return first + "(by: " + date.format(DateTimeFormatter
                .ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns a Task object specified by the input string.
     *
     * @param s input message string
     * @return a Task object
     * @throws IllegalArgumentException  if cannot detect task type correctly
     */
    static Task add(String s) throws IllegalTaskTypeException {
        String taskType = s.split(" ; ")[0];
        String isDone = s.split(" ; ")[1];
        String message = s.split(" ; ")[2];

        switch (taskType) {
        case "T":
            return new Todo(message, getStatus(Integer.parseInt(isDone)));
        case "E":
            return new Event(message, getStatus(Integer.parseInt(isDone)));
        case "D":
            return new Deadline(message, getStatus(Integer.parseInt(isDone)));
        default:
            throw new IllegalTaskTypeException();
        }
    }

    /**
     * Returns a boolean status from an integer representation.
     *
     * @param i  a number to denote false or true (0 for false, 1 for true)
     *           here, we assume the input number is correct
     * @return boolean status
     */
    static boolean getStatus(int i) {
        return i != 0;
    }
}
