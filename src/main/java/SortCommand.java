import java.util.ArrayList;

/**
 * Class to handle sort commands entered by the user.
 * @author vanGoghhh
 */

public class SortCommand extends Command {

    private String userCommand;

    /**
     * Constructor for the sort command.
     * @param userCommand Complete line of command entered by the user.
     */
    public SortCommand(String userCommand) {
        this.userCommand = userCommand;
    }

    /**
     * Method to execute entirely when a done command is entered by the user.
     * @param tasks Tasklist containing all the tasks.
     * @param ui UI to print string responses by the bot.
     * @return the String response for this command.
     */
    @Override
    String execute(TaskList tasks, UI ui) {
            ArrayList<Task> sortedTasks = tasks.sortTask();
            String dukeResponse = ui.sortTasks(sortedTasks);
            return dukeResponse;

    }
}
