package duke.parser;

import duke.dukeException.DukeException;

import java.util.Arrays;
import java.util.Collections;

/**
 * This class handles the user inputs of task details.
 */
public class Parser {

    /**
     * User Input is parsed here for processing, enable Event and Deadline objects to be created.
     *
     * @param str User Input string.
     * @return A string array that consists of Task Details and DateTime.
     * @throws DukeException If details and/or date is missing.
     */
    public static String[] parseDetails(String str) throws DukeException {
        String[] s;
        if (str.contains("/by")) {
            s = str.split(" /by ");
            if (s.length <= 1) {
                throw new DukeException("Yo! Details/Date [Time] are missing.");
            }
            return s;
        } else if (str.contains("/at")) {
            s = str.split(" /at ");
            if (s.length <= 1) {
                throw new DukeException("Yo! Details/Date [Time] are missing.");
            }
            return s;
        } else {
            throw new DukeException(
                    "Yo! Command Syntax Error. '<Details> /by or /at <dd/MM/yy [HH:MM]>'");
        }
    }

    /**
     * User Input is parsed here for processing, enable multiple task numbers to be marked as done or
     * deleted.
     *
     * @param str User Input string; task numbers.
     * @return An integer array that consists of task number(s).
     * @throws DukeException If integer array is empty.
     */
    public static int[] parse(String str) throws DukeException {
        int[] intArray;
        if (str.isBlank()) {
            throw new DukeException("Yo! Enter the task numbers(s).");
        }
        intArray =
                Arrays.stream(str.split(" "))
                        .sorted(Collections.reverseOrder())
                        .mapToInt(Integer::parseInt)
                        .toArray();
        return intArray;
    }
}
