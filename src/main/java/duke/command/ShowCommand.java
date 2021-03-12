package duke.command;

import static duke.util.Keyword.EMPTY_STRING;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Shows all tasks in the TaskList object.
 */
public class ShowCommand extends Command {

    /**
     * Shows all tasks in the TaskList object if and only if task list is not empty.
     *
     * @param taskList TaskList object.
     * @param ui User Interface object.
     * @param storage Storage object.
     * @return Response message to user.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.isEmpty()) {
            return ui.emptyTaskList();
        } else {
            return ui.showTaskList(taskList, EMPTY_STRING);
        }
    }
}
