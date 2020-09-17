package Mattbot.Command;

import Mattbot.UI.UserInterface;

/**
 * Represents a command that exits the program.
 */
public class ExitCommand extends Command {

    /**
     * Calls the UserInterface to print the stop message.
     * Returns the exit message.
     *
     * @return String exit message.
     */
    public static String execute2() {
        return UserInterface.stop2();
    }
}
