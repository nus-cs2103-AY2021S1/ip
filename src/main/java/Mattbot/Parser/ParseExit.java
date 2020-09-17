package Mattbot.Parser;

import Mattbot.Command.ExitCommand;

/**
 * Represents the bridge that calls the appropriate exit command.
 */
public class ParseExit extends Parse {

    /**
     * Calls the exit command.
     * Returns the exiting message.
     *
     * @return String exiting message.
     */
    public static String execute2() {
        return ExitCommand.execute2();
    }
}
