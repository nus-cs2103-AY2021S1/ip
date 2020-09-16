package duke.command;

import duke.Duke;
import duke.exception.BlankTagException;
import duke.exception.ExceptionMessage;
import duke.exception.IncorrectFormatException;
import duke.exception.InvalidIndexException;
import duke.task.Task;
import duke.util.ParseUtil;

public class TagCommand extends Command {

    /**
     * Constructs a TagCommand.
     */
    public TagCommand() {
        names = new String[] { "tag" };
        description = "Tags a task in the task list with some string.\nFormat: " + CommandFormat.TAG_CMD_FORMAT;
    }

    /**
     * Tags the task with the input index with some string.
     * @param str the info needed for execution
     * @param duke the current Duke
     */
    @Override
    public void execute(String str, Duke duke) {
        String[] splitStr = str.split(" ", 2);

        checkException(splitStr);

        int taskIndex = Integer.parseInt(splitStr[0]);
        String tag = splitStr[1];
        Task task = duke.getTaskList().getTask(taskIndex);

        task.setTag(tag);

        response(task, duke);
    }

    private void response(Task task, Duke duke) {
        if (duke.getState().getUseGui()) {
            duke.getGuiResponse().reportTagTask(task);
        } else {
            duke.getUiResponse().reportTagTask(task);
        }
    }

    private void checkException(String[] splitStr) {
        if (splitStr.length != 2) {
            String errMessage = ExceptionMessage.TAG_INCORRECT_FORMAT_MESSAGE;
            throw new IncorrectFormatException(errMessage);
        }

        if (!ParseUtil.canParseInteger(splitStr[0])) {
            String errMessage = ExceptionMessage.getInvalidIndexMessage(splitStr[0]);
            throw new InvalidIndexException(errMessage);
        }

        if (splitStr[1].isBlank()) {
            String errMessage = ExceptionMessage.BLANK_TAG_MESSAGE;
            throw new BlankTagException(errMessage);
        }
    }
}
