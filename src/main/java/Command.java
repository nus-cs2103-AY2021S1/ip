/**
 * Abstract class for handling users command to the bot
 * @author vanGoghhh
 */

public abstract class Command {

    /**
     * Abstract method which would be implemented in the subclasses
     * @param tasks
     * @param ui
     * @throws DukeException
     */
    abstract void execute(TaskList tasks, UI ui) throws DukeException;

    /**
     * Abstract method which would be implemented in the subclasses
     * @return a boolean to indicate whether or not to exit the bot
     */
    abstract boolean isExit();
}
