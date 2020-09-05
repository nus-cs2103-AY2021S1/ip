package focus.command;

import focus.exception.FocusException;
import focus.exception.InvalidHelpCommandException;
import focus.storage.Storage;
import focus.task.TaskList;
import focus.ui.UI;

/** Represents the HelpCommand to help first-time users about the commands. */
public class HelpCommand extends Command {
    /**
     * Returns false since HelpCommand is not an ExitCommand.
     *
     * @return False.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes HelpCommand help users with the commands.
     *
     * @param input User's input.
     * @param taskList Task list created for user.
     * @param storage Storage created for user.
     * @return String representation of list of commands.
     * @throws FocusException If input does not meet criteria.
     */
    public String execute(String input, TaskList taskList, Storage storage) throws FocusException {
        String checker;
        if (input.length() == 4) { // user's input is "help"
            return UI.listCommands();
        }
        assert !(input.length() <= 4) : "Input length should be more than 4 here.";
        try {
            checker = input.split("help")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidHelpCommandException();
        }
        if (!checker.isBlank()) { // user's input is "help me" for example
            throw new InvalidHelpCommandException();
        } else { // user's input is "help " with spacings
            assert !checker.isEmpty() : "Checker should not be blank here.";
            return UI.listCommands();
        }
    }
}
