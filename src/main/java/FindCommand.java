import java.util.ArrayList;

/**
 * Class to handle a find command entered by user.
 * @author vanGoghhh
 */

public class FindCommand extends Command {

    private String command;

    /**
     * Constructor for findcommand object.
     * @param command Complete line of user command.
     */
    public FindCommand(String command) {
        this.command = command;
    }

    /**
     * Method to execute when a find command is entered.
     * @param tasks TaskList containing all the tasks
     * @param dukeUI UI to print string responses by the bot.
     * @throws DukeException
     */
    @Override
    protected String execute(TaskList tasks, UI dukeUI) throws DukeException {
        try {
            String[] keywords = this.command.split(" ", 2);
            String keyword = keywords[1];
            ArrayList<Task> foundTasks = tasks.findTask(keyword);
            String dukeResponse = dukeUI.findTask(foundTasks);
            return dukeResponse;
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidKeywordException();
        }
    }

    /**
     * Method to tell bot to end current session or not.
     * @return false to not end session.
     */
    @Override
    boolean isExit() {
        return false;
    }
}
