package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.UI;

/**
 * Represents the HelpCommand to help first-time users about the commands.
 */
public class HelpCommand extends Command {
    /**
     * Returns false since HelpCommand is not an ExitCommand.
     * @return False.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes HelpCommand help users with the commands.
     * @param input User's input.
     * @param taskList Task list created for user.
     * @param storage Storage created for user.
     * @throws DukeException If input does not meet criteria.
     */
    public void execute(String input, TaskList taskList, Storage storage) throws DukeException {
        String checker;
        if (input.length() == 4) { // user's input is "help"
            UI.listCommands();
            return;
        }
        try {
            checker = input.split("help")[1];
        }
        catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("\tDid you meant the command 'help'?");
        }
        if (!checker.isBlank()) { // user's input is "help me" for example
            throw new DukeException("\tDid you meant the command 'help'?");
        } else { // user's input is "help " with spacings
            UI.listCommands();
        }
    }
}
