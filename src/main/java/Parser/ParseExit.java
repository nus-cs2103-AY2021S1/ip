package Parser;

import Command.ExitCommand;

/**
 * Represents the bridge that calls the appropriate exit command.
 */
public class ParseExit extends Parse {

    /**
     * Calls the exit command.
     */
    public static void execute() {
        ExitCommand.execute();
    }
}
