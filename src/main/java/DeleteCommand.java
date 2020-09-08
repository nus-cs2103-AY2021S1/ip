/**
 * Represents a delete command.
 *
 * @author Siqi
 * @version 1.1
 * @since 2020-09-08
 */
public class DeleteCommand extends Command {
    private final String command;
    private final int DELETE_LENGTH = 6;

    public DeleteCommand(String command) {
        this.command = command;
    }

    private String initiateDeleteTask(String taskNumberString, TaskList taskList) throws DukeException {
        int taskNumber = Integer.parseInt(taskNumberString);
        if (taskNumber >= 1) { //if input index is valid
            return taskList.deleteTask(taskNumber);
        } else {
            throw new DukeException("Please enter a valid task number "
                    + "to delete (index is not valid)");
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
        String taskNumberString = this.command.substring(DELETE_LENGTH).trim();
        boolean isTaskNumberEmpty = taskNumberString.isEmpty();
        boolean isTaskNumberANumber = taskNumberString.matches("[0-9]+");
        if (!isTaskNumberEmpty && isTaskNumberANumber) {
            return initiateDeleteTask(taskNumberString, taskList);
        } else {
            throw new DukeException("Please enter a valid task number to"
                    + " delete (substring doesn't match regex)");
        }
    }
}
