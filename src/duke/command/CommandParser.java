package duke.command;

import duke.data.DukeCommandSet;

import duke.exception.IncorrectFormatException;
import duke.exception.InvalidIndexException;
import duke.exception.NoDescriptionException;
import duke.exception.UnknownCommandException;

public class CommandParser {

    public static void parse(String str) {
        String[] splitParts = splitInputLine(str);
        String commandName = splitParts[0];
        String rest = splitParts[1];

        Command command = tryGetCommand(commandName);

        if (command != null) {
            tryExecuteCommand(command, rest);
        }
    }

    private static String[] splitInputLine(String inputLine) {
        String[] splitParts = new String[2];

        String[] inputParts = inputLine.split(" ", 2);
        splitParts[0] = inputParts[0];
        splitParts[1] = inputParts.length == 2 ? inputParts[1] : "";

        return splitParts;
    }

    private static Command tryGetCommand(String commandName) {
        Command command = null;

        try {
            command = DukeCommandSet.getInstance().getCommand(commandName);
        } catch (UnknownCommandException exception) {
            System.out.println(exception.getMessage());
        }

        return command;
    }

    private static void tryExecuteCommand(Command command, String rest) {
        try {
            command.execute(rest);
        } catch (NoDescriptionException | IncorrectFormatException | InvalidIndexException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
