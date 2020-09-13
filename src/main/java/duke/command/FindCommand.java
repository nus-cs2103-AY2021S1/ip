package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * The FindCommand class implements a
 * FindCommand command executable to find a task
 * based on a keyword, from the task list.
 *
 * @author Amy Lim Zhi Ting
 * @version v0.1
 */
public class FindCommand extends Command {
    protected String input;

    public FindCommand(String input) {
        this.input = input;
    }

    /**
     * Executes finding of task in TaskList that matches keyword from user
     * and shows success/error information.
     *
     * @param storage Storage data of tasks in hard disk.
     * @param taskList TaskList where finding of task that matches is done.
     * @param ui Ui that shows success/error messages from the finding action.
     */
    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        try {
            ui.showSuccess(taskList.findTask(this.input));
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Returns string success/error information after execution of
     * finding of task in TaskList.
     *
     * @param storage Storage data of tasks in hard disk.
     * @param taskList TaskList where finding of task that matches is done.
     * @param ui Ui that shows success/error messages from the finding action.
     * @return String success/error information
     */
    @Override
    public String executeToString(Storage storage, TaskList taskList, Ui ui) {
        try {
            return taskList.findTask(this.input);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
