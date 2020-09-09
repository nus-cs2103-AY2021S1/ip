package duke.command;

import duke.Duke;
import duke.exception.ExceptionMessage;
import duke.exception.InvalidIndexException;
import duke.task.Task;

/**
 * DeleteCommand asks DukeTaskList to remove the task with the input index.
 */
public class DeleteCommand extends Command {

    /**
     * Constructs a DeleteCommand.
     */
    public DeleteCommand() {
        names = new String[] { "delete" };
        description = "Deletes a task using its index\nFormat: " + CommandFormat.DELETE_CMD_FORMAT;
    }

    /**
     * Asks DukeTaskList to remove the task with the input index.
     * @param str the index of the task
     * @param duke the current Duke
     * @throws InvalidIndexException thrown when the index is invalid
     */
    @Override
    public void execute(String str, Duke duke) throws InvalidIndexException {
        checkException(str);

        int taskIndex = Integer.parseInt(str);

        Task task = duke.getTaskList().deleteTask(taskIndex);

        response(task, duke);
    }

    private void response(Task task, Duke duke) {
        if (duke.getState().getUseGui()) {
            duke.getGuiResponse().reportDeleteTask(task);
        } else {
            duke.getUiResponse().reportDeleteTask(task);
        }
    }

    private boolean canParseInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    private void checkException(String str) {
        if (!canParseInt(str)) {
            String errMessage = ExceptionMessage.getInvalidIndexMessage(str);
            throw new InvalidIndexException(errMessage);
        }
    }
}
