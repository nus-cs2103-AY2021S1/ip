package Command;

import UI.UserInterface;

/**
 * Represents a command that exits the program.
 */
public class ExitCommand extends Command{

    /**
     * Calls the UserInterface to print the stop message.
     */
    public static void execute(){
        UserInterface.stop();
    }
}
