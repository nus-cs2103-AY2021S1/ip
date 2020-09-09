package duke.command;

import duke.Duke;
import duke.exception.ExceptionMessage;
import duke.exception.InvalidIndexException;
import duke.task.Task;

/**
 * DoneCommand marks the task with the index as done, reports to the user.
 */
public class DoneCommand extends Command {

    /**
     * Constructs a DoneCommand.
     */
    public DoneCommand() {
        names = new String[] { "done" };
        description = "Marks a task as done using its index\nFormat: " + CommandFormat.DONE_CMD_FORMAT;
    }

    /**
     * Marks the task with the index as done, reports to the user.
     * @param str the index of the task
     * @param duke the current Duke
     * @throws InvalidIndexException thrown when the index is invalid
     */
    @Override
    public void execute(String str, Duke duke) throws InvalidIndexException {
        checkException(str);

        int taskIndex = Integer.parseInt(str);

        Task task = duke.getTaskList().getTask(taskIndex);
        task.setTaskDone(true);

        response(task, duke);
    }

    private void response(Task task, Duke duke) {
        if (duke.getState().getUseGui()) {
            duke.getGuiResponse().reportDoneTask(task);
        } else {
            duke.getUiResponse().reportDoneTask(task);
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
