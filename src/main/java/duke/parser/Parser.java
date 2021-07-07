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
     * @param inputString User Input string.
     * @return A string array that consists of Task Details and DateTime.
     * @throws DukeException If details and/or date is missing.
     */
    public static String[] parseDetails(String inputString) throws DukeException {
        String[] splitStrings;
        if (inputString.contains("/by")) {
            splitStrings = inputString.split(" /by ");
            if (splitStrings.length <= 1) {
                throw new DukeException("Yo! Details/Time are missing.");
            }
            return splitStrings;
        } else if (inputString.contains("/at")) {
            splitStrings = inputString.split(" /at ");
            if (splitStrings.length <= 1) {
                throw new DukeException("Yo! Details/Time are missing.");
            }
            return splitStrings;
        } else {
            throw new DukeException(
                    "Yo! Command Syntax Error. '<Details> /by or /at <dd/MM/yy [HH:MM]>'");
        }
    }

    /**
     * User Input is parsed here for processing, enable multiple task numbers to be marked as done or
     * deleted. (C-MassOps)
     *
     * @param inputString User Input string; task numbers.
     * @return An integer array that consists of task number(s).
     * @throws DukeException If integer array is empty.
     */
    public static int[] parseMultipleTasks(String inputString) throws DukeException {
        int[] taskNumbers;
        if (inputString.isBlank()) {
            throw new DukeException("Yo! Enter the task numbers(s).");
        }
        try {
            taskNumbers =
                    Arrays.stream(inputString.split(" "))
                            .sorted(Collections.reverseOrder())
                            .mapToInt(Integer::parseInt)
                            .toArray();

        } catch (NumberFormatException e) {
            throw new DukeException("Yo! Enter the task numbers(s) correctly.");
        }
        assert taskNumbers != null : "Error in stream operations";
        return taskNumbers;
    }

    /**
     * User Command and Details Input is parsed here for processing, to split command keyword and details.
     *
     * @param inputString User Input string; User Command and Details.
     * @return A string array with command at index 0 and details at index 1.
     */
    public static String[] parseCommand(String inputString) {
        String[] splitStrings;
        splitStrings = inputString.split(" ", 2);
        return splitStrings;
    }
}
