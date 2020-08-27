package Parser;

import Command.ExitCommand;
import Errors.ErrorExceptions;
import UI.UserInterface;

/**
 * Represents the bridge that calls the appropriate exit command.
 */
public class ParseExit extends Parse{

    /**
     * Calls the exit command.
     */
    public static void execute() {
        ExitCommand.execute();
    }
}
