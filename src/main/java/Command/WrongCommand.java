package Command;

import UI.UserInterface;

/**
 * Represents a command that calls to print the wrong inout message.
 */
public class WrongCommand extends Command {

    /**
     * Calls UserInterface to print the error message.
     */
    public static void execute() {
        UserInterface.wrongCommand();
    }

    public static String execute2() {
        return UserInterface.wrongCommand2();
    }
}
