package duke.command;

import duke.Duke;
import duke.exception.ExceptionMessage;
import duke.exception.InvalidIndexException;
import duke.task.Task;
import duke.util.ParseUtil;

/**
 * DoneCommand marks the task with the index as done, reports to the user.
 */
public class DoneCommand extends Command {

    /**
     * Constructs a DoneCommand.
     */
    public DoneCommand() {
        names = new String[] { "done" };
        description = "Marks a task as done using its index.";
        format = CommandFormat.DONE_CMD_FORMAT;
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
        task.setDone(true);

        response(task, duke);
    }

    private void response(Task task, Duke duke) {
        if (duke.getState().getUseGui()) {
            duke.getGuiResponse().reportDoneTask(task);
        } else {
            duke.getUiResponse().reportDoneTask(task);
        }
    }

    private void checkException(String str) {
        if (!ParseUtil.canParseInteger(str)) {
            String errMessage = ExceptionMessage.getInvalidIndexMessage(str);
            throw new InvalidIndexException(errMessage);
        }
    }
}
