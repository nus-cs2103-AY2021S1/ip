package duke.command;

import duke.Duke;
import duke.exception.ExceptionMessage;
import duke.exception.InvalidIndexException;
import duke.task.Task;
import duke.util.ParseUtil;

public class UntagCommand extends Command {

    /**
     * Constructs an UntagCommand.
     */
    public UntagCommand() {
        names = new String[] { "untag" };
        description = "Deletes the tag of the task with the given index.";
        format = CommandFormat.UNTAG_CMD_FORMAT;
    }

    @Override
    public void execute(String str, Duke duke) {
        checkException(str);

        int taskIndex = Integer.parseInt(str);

        Task task = duke.getTaskList().getTask(taskIndex);
        task.setTag(Task.NO_TAG);

        response(task, duke);
    }

    private void response(Task task, Duke duke) {
        if (duke.getState().getUseGui()) {
            duke.getGuiResponse().reportUntagTask(task);
        } else {
            duke.getUiResponse().reportUntagTask(task);
        }
    }

    private void checkException(String str) {
        if (!ParseUtil.canParseInteger(str)) {
            String errMessage = ExceptionMessage.getInvalidIndexMessage(str);
            throw new InvalidIndexException(errMessage);
        }
    }
}
