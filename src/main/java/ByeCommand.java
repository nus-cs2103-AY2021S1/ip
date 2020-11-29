/**
 * Class for the command to exit the bot.
 * @author vanGoghhh
 */

public class ByeCommand extends Command {

    /**
     * Returns a string to indicate the exiting of the bot.
     * @param tasks
     * @param ui
     * @throws DukeException
     */
    @Override
    protected String execute(TaskList tasks, UI ui) throws DukeException {
        return "Bye Have a nice day!";
    }

    /**
     * Return a boolean to indicate the exiting of the bot.
     * @return true to exit the command.
     */
    protected boolean isExit() {
        return true;
    }
}
