/**
 * Abstract class for handling users command to the bot.
 * @author vanGoghhh
 */

public abstract class Command {

    /**
     * Abstract method which would be implemented in the subclasses.
     * @param tasks
     * @param ui
     * @return string response by the bot.
     * @throws DukeException
     */
    abstract String execute(TaskList tasks, UI ui) throws DukeException;

}
