package duke.command;

import duke.Duke;
import duke.exception.DukeException;
import duke.exception.UnknownCommandException;

/**
 * CommandParser split user's input string line into parts, then try to figure
 * out the command to executed and try to execute it. If the input line
 * cannot be understood by CommandParser, it would reports that to the user.
 */
public class CommandParser {

    /**
     * Tries to split the input string, then tries to figure out the command,
     * and tries to execute if a command is figured out.
     * @param str the input line
     * @param duke the current Duke
     */
    public void parse(String str, Duke duke) {
        String[] splitParts = splitInputLine(str);
        String commandName = splitParts[0];
        String rest = splitParts[1];

        Command command = tryGetCommand(commandName, duke);

        if (command != null) {
            tryExecuteCommand(command, rest, duke);
        }
    }

    private String[] splitInputLine(String inputLine) {
        String[] splitParts = new String[2];

        String[] inputParts = inputLine.split(" ", 2);
        splitParts[0] = inputParts[0];
        splitParts[1] = inputParts.length == 2 ? inputParts[1].trim() : "";

        return splitParts;
    }

    private Command tryGetCommand(String commandName, Duke duke) {
        Command command = null;

        try {
            command = duke.getCommandSet().getCommand(commandName);
        } catch (UnknownCommandException exception) {
            reportException(exception, duke);
        }

        return command;
    }

    private void tryExecuteCommand(Command command, String rest, Duke duke) {
        try {
            command.execute(rest, duke);
        } catch (DukeException exception) {
            reportException(exception, duke);
        }
    }

    private void reportException(DukeException exception, Duke duke) {
        if (duke.getState().getUseGui()) {
            duke.getGuiResponse().reportException(exception);
        } else {
            duke.getUiResponse().reportException(exception);
        }
    }
}
