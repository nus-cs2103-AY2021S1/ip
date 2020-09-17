package mattbot.command;

import mattbot.uI.UserInterface;

/**
 * Represents a command that calls to print the wrong inout message.
 */
public class WrongCommand extends Command {

    /**
     * Calls UserInterface to print the error message.
     * Returns the error message.
     *
     * @return String wrong message.
     */
    public static String execute2() {
        return UserInterface.wrongCommand2();
    }
}
