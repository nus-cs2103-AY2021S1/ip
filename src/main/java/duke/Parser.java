package duke;

import duke.commands.Command;
import duke.tasks.TaskType;


/**
 * Parser for Duke.
 * Parses user inputs.
 */
public class Parser {

    /**
     * Parse input line to array by delimiter.
     *
     * @param input input line.
     * @return parsed string array.
     */
    public static String[] parseLineToArray(String input) {
        return input.split(" ");
    }


    /**
     * Parses line and gets command.
     *
     * @param input input line.
     * @return command.
     */
    public static Command getCommand(String input) {
        String keyword = parseLineToArray(input)[0];

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
     * @param input input line.
     * @return task type.
     */
    public static TaskType getTaskKeyword(String input) {
        String keyword = parseLineToArray(input)[0];

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

}
