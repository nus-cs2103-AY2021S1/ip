package duke.command;

import duke.Duke;
import duke.data.DukeCommandSet;

import duke.exception.IncorrectFormatException;
import duke.exception.InvalidIndexException;
import duke.exception.NoDescriptionException;
import duke.exception.UnknownCommandException;

public class CommandParser {

    public void parse(String str, Duke duke) {
        String[] splitParts = splitInputLine(str);
        String commandName = splitParts[0];
        String rest = splitParts[1];

        Command command = tryGetCommand(commandName, duke.commandSet);

        if (command != null) {
            tryExecuteCommand(command, rest, duke);
        }
    }

    private String[] splitInputLine(String inputLine) {
        String[] splitParts = new String[2];

        String[] inputParts = inputLine.split(" ", 2);
        splitParts[0] = inputParts[0];
        splitParts[1] = inputParts.length == 2 ? inputParts[1] : "";

        return splitParts;
    }

    private Command tryGetCommand(String commandName, DukeCommandSet commandSet) {
        Command command = null;

        try {
            command = commandSet.getCommand(commandName);
        } catch (UnknownCommandException exception) {
            System.out.println(exception.getMessage());
        }

        return command;
    }

    private void tryExecuteCommand(Command command, String rest, Duke duke) {
        try {
            command.execute(rest, duke);
        } catch (NoDescriptionException | IncorrectFormatException | InvalidIndexException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
