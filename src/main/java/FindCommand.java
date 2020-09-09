/**
 * Represents a find command.
 *
 * @author Siqi
 * @version 1.1
 * @since 2020-09-08
 */
public class FindCommand extends Command {
    private final String command;
    private final int FIND_LENGTH = 4;

    public FindCommand(String command) {
        this.command = command;
    }

    private TaskList initiateFind(final TaskList taskList) throws DukeException {
        String queryString = this.command.substring(FIND_LENGTH).trim();
        boolean isQueryStringEmpty = queryString.isEmpty();
        if (!isQueryStringEmpty) {
            return taskList.findTasks(queryString);
        } else {
            throw new DukeException("Please enter a valid search item");
        }
    }

    /**
     * Executes the command.
     *
     * @param storage   The storage object that handles interactions with the
     *                  local file.
     * @param ui        The UI object that handles interactions with the user.
     * @param taskList  The current list of tasks.
     * @return          The string to be displayed to the user.
     * @throws DukeException    When the input command is invalid.
     */
    @Override
    public String execute(Storage storage, Ui ui, TaskList taskList) throws DukeException {
        TaskList matchingTasks = initiateFind(taskList);
        return ui.printMatchingTasks(matchingTasks);
    }
}
