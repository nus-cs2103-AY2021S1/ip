package duke.command;

import java.util.ArrayList;

import duke.Duke;
import duke.exception.EmptyKeywordException;
import duke.exception.ExceptionMessage;
import duke.task.Task;

/**
 * FindCommand finds all tasks that contain the keyword entered by user,
 * then report to user.
 */
public class FindCommand extends Command {

    /**
     * Constructs a FindCommand.
     */
    public FindCommand() {
        names = new String[] { "find" };
        description = "Finds the tasks containing the keyword.";
        format = CommandFormat.FIND_CMD_FORMAT;
    }

    /**
     * Finds all tasks containing the keyword using DukeTaskList,
     * then use UiResponse to report to the user.
     * @param str the keyword
     * @param duke the current Duke
     */
    @Override
    public void execute(String str, Duke duke) {
        checkException(str);
        ArrayList<Task> tasksFound = duke.getTaskList().findTasks(str);
        response(tasksFound, duke);
    }

    private void response(ArrayList<Task> tasksFound, Duke duke) {
        if (duke.getState().getUseGui()) {
            duke.getGuiResponse().reportGivenTasks(tasksFound);
        } else {
            duke.getUiResponse().reportGivenTasks(tasksFound);
        }
    }

    private void checkException(String str) {
        if (str.isBlank()) {
            throw new EmptyKeywordException(ExceptionMessage.EMPTY_KEYWORD_MESSAGE);
        }
    }
}
