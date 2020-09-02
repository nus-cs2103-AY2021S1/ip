package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * The ListCommand class implements a
 * ListCommand command executable to list
 * out all the tasks in the task list.
 *
 * @author Amy Lim Zhi Ting
 * @version v0.1
 */
public class ListCommand extends Command {
    protected String input;

    public ListCommand() {
    }

    /**
     * Executes listing of task in TaskList and shows error information.
     *
     * @param storage Storage data in hard disk.
     * @param taskList TaskList where task list is printed.
     * @param ui Ui that shows error message from the action.
     */
    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        try {
            ui.showSuccess(taskList.printList());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Returns string of success/error information from execution of
     * printing of task list.
     *
     * @param storage Storage data in hard disk.
     * @param taskList TaskList where task list is printed.
     * @param ui Ui that shows error message from the action.
     * @return String string of success/error information
     */
    @Override
    public String executeToString(Storage storage, TaskList taskList, Ui ui) {
        try {
            return taskList.printList();
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
