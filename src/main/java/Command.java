import java.io.IOException;

/**
 * The abstract Command class is used for extension to the different commands for different interaction
 * with the saved TaskList and output printed to the user.
 */
abstract class Command {
    /**
     * This abstract method is inherited by all child classes of the Command class
     * for different interaction with the saved TaskList and updating
     * the duke.txt file.
     * @param tasks This is the saved TaskList in duke.txt.
     * @return A boolean to indicate whether the program should be exited.
     */
    abstract boolean execute(TaskList tasks);
}
