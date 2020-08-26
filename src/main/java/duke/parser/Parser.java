package duke.parser;

import duke.dukeException.DukeException;

import java.util.Arrays;
import java.util.Collections;

public class Parser {

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

    public static int[] parse(String inputString) throws DukeException {
        int[] taskNumbers;
        if (inputString.isBlank()) {
            throw new DukeException("Yo! Enter the task numbers(s).");
        }
        taskNumbers =
                Arrays.stream(inputString.split(" "))
                        .sorted(Collections.reverseOrder())
                        .mapToInt(Integer::parseInt)
                        .toArray();
        return taskNumbers;
    }
}
