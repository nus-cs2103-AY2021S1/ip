package duke;

import duke.exception.DukeException;

import java.util.Arrays;

/**
 * A class deals with making sense of the user command.
 */
public class Parser {

    /**
     * Parse the full command to a Array of String.
     * @param command the full command from user's input.
     * @return a Array of String parsed from the full command.
     * @throws DukeException
     */
    public static String[] parse(String command) throws DukeException {
        String[] commands = command.split(" ", 2);
        String[] temp;
        commands = Arrays.copyOf(commands, 3);
        String key;
        switch (commands[0]) {
        case "bye":
        case "list": {
            return commands;
        }
        case "done": {
            if (!isNumeric(commands[1])) {
                throw new DukeException(" ☹ OOPS!!! The argument of done command is not a number.");
            }
            return commands;
        }
        case "delete": {
            if (!isNumeric(commands[1])) {
                throw new DukeException(" ☹ OOPS!!! The argument of delete command is not a number.");
            }
            return commands;
        }
        case "todo":
            if (commands[1] == null || commands[1].isBlank()) {
                throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");
            }
            return commands;
        case "deadline":
            key = " /by ";
            if (commands[1].isBlank()) {
                throw new DukeException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            if (!commands[1].contains(key)) {
                throw new DukeException(" ☹ OOPS!!! The description of a deadline must contain /by keywords");
            }
            temp = commands[1].split(key);
            commands[1] = temp[0];
            commands[2] = temp[1];
            return commands;
        case "event":
            key = " /at ";
            if (commands[1].isBlank()) {
                throw new DukeException(" ☹ OOPS!!! The description of a event cannot be empty.");
            }
            if (!commands[1].contains(key)) {
                throw new DukeException(" ☹ OOPS!!! The description of a event must contain /at keywords");
            }
            temp = commands[1].split(key);
            commands[1] = temp[0];
            commands[2] = temp[1];
            return commands;
        default:  //meaningless command
            throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!(Character.isDigit(str.charAt(i)) || str.charAt(i) == ' ')) {
                return false;
            }
        }
        return true;
    }
}
