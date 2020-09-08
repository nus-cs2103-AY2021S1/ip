/**
 * Represents a todo command.
 *
 * @author Siqi
 * @version 1.1
 * @since 2020-09-08
 */
public class TodoCommand extends Command {
    /**
     * The string containing information about the todo task.
     */
    private final String command;
    /**
     * The length of the string "todo"
     */
    private final int TODO_LENGTH = 4;

    /**
     * Todo constructor.
     *
     * @param command   The string containing information about the todo task.
     */
    public TodoCommand(String command) {
        this.command = command;
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
        String desc = this.command.substring(TODO_LENGTH).trim();
        boolean isDescEmpty = desc.isEmpty();
        if (!isDescEmpty) {
            return taskList.addTodo(this.command);
        } else {
            throw new DukeException("Please enter a valid todo");
        }
    }
}
