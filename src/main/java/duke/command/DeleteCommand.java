package duke.command;

import duke.Duke;
import duke.exception.ExceptionMessage;
import duke.exception.InvalidIndexException;
import duke.task.Task;
import duke.util.ParseUtil;

/**
 * DeleteCommand asks DukeTaskList to remove the task with the input index.
 */
public class DeleteCommand extends Command {

    /**
     * Constructs a DeleteCommand.
     */
    public DeleteCommand() {
        names = new String[] { "delete" };
        description = "Deletes a task using its index.";
        format = CommandFormat.DELETE_CMD_FORMAT;
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

    private void checkException(String str) {
        if (!ParseUtil.canParseInteger(str)) {
            String errMessage = ExceptionMessage.getInvalidIndexMessage(str);
            throw new InvalidIndexException(errMessage);
        }
    }
}
