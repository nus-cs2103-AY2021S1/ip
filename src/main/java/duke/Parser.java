package duke;


import duke.commands.Command;
import duke.exceptions.DukeInvalidDescriptionException;
import duke.exceptions.DukeNoDescriptionException;
import duke.tasks.TaskType;

import java.util.Arrays;


/**
 * Parser for Duke.
 * Parses user inputStrings.
 */
public class Parser {

    /**
     * Parse inputString to array by delimiter.
     *
     * @param inputString inputString line.
     * @return parsed string array.
     */
    public static String[] parseLineToArray(String inputString) {
        return inputString.split(" ");
    }


    /**
     * Parses line and gets command.
     *
     * @param inputString input line.
     * @return command.
     */
    public static Command getCommand(String inputString) {
        String keyword = parseLineToArray(inputString)[0];

        switch (keyword) {
        case ("list"):
            return Command.LIST;
        case ("delete"):
            return Command.DELETE;
        case ("done"):
            return Command.DONE;
        case ("bye"):
            return Command.TERMINATE;
        case ("todo"):
        case ("event"):
        case ("deadline"):
            return Command.TASK;
        default:
            return Command.INVALID;
        }
    }


    /**
     * Parses line and gets task type.
     *
     * @param inputString input line.
     * @return task type.
     */
    public static TaskType getTaskKeyword(String inputString) {
        String keyword = parseLineToArray(inputString)[0];

        switch (keyword) {
        case ("todo"):
            return TaskType.TODO;
        case ("event"):
            return TaskType.EVENT;
        case ("deadline"):
            return TaskType.DEADLINE;
        default:
            return TaskType.INVALID;
        }

    }


    /**
     * Removes the keyword at the start of the string array.
     *
     * @param strArr Array of strings (originally split by spaces).
     * @return Substring with the keyword removed.
     * @throws DukeInvalidDescriptionException invalid description text.
     */
    public static String getItemSubstring(String[] strArr) throws DukeInvalidDescriptionException {
        if (strArr.length <= 1) {
            // string array is of invalid length
            throw new DukeNoDescriptionException("String array of unexpected length: expected length > 1");
        } else {
            return String.join(" ", Arrays.copyOfRange(strArr, 1, strArr.length));
        }
    }

}
