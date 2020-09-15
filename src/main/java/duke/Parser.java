package duke;

import java.util.Arrays;

import duke.exception.DukeException;
import duke.operation.Operation;

/**
 * A class deals with making sense of the user command.
 */
public class Parser {

    /**
     * Parses the full command to a Array of String.
     *
     * @param command the full command from user's input.
     * @return a Array of String parsed from the full command.
     * @throws DukeException
     */
    public static String[] parse(String command) throws DukeException {
        String[] commands = command.split(" ", 2);
        if (commands.length > 1 && commands[1] != null) {
            commands[1] = commands[1].trim();
        }
        String[] temp;
        String key;
        commands = Arrays.copyOf(commands, 3);
        switch (Operation.toOperation(commands[0])) {
        case BYE:
        case FIND:
        case LIST: {
            return commands;
        }
        case DONE: {
            if (!isNumeric(commands[1])) {
                throw new DukeException(" Oops. The argument of done command is not a number.");
            }
            return commands;
        }
        case DELETE: {
            if (!isNumeric(commands[1])) {
                throw new DukeException(" Oops. The argument of delete command is not a number.");
            }
            return commands;
        }
        case TODO:
            if (commands[1] == null || commands[1].isBlank()) {
                throw new DukeException(" OOPS!!! The description of a todo cannot be empty.");
            }
            return commands;
        case DEADLINE:
            key = " /by ";
            if (commands[1].isBlank()) {
                throw new DukeException(" OOPS!!! The description of a deadline cannot be empty.");
            }
            if (!commands[1].contains(key)) {
                throw new DukeException(" OOPS!!! The description of a deadline must contain /by keywords");
            }
            temp = commands[1].split(key);
            commands[1] = temp[0];
            commands[2] = temp[1];
            return commands;
        case EVENT:
            key = " /at ";
            if (commands[1].isBlank()) {
                throw new DukeException(" OOPS!!! The description of a event cannot be empty.");
            }
            if (!commands[1].contains(key)) {
                throw new DukeException(" OOPS!!! The description of a event must contain /at keywords");
            }
            temp = commands[1].split(key);
            commands[1] = temp[0];
            commands[2] = temp[1];
            return commands;
        default:
            throw new DukeException(" OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
