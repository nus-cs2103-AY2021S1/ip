package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * The DeleteCommand class implements a DeleteCommand command
 * executable to delete a task from the task list.
 *
 * @author Amy Lim Zhi Ting
 * @version v0.1
 */
public class DeleteCommand extends Command {
    protected String input;

    public DeleteCommand(String input) {
        this.input = input;
    }


    /**
     * Executes deletion of task in TaskList and shows success/error information.
     *
     * @param storage Storage where deletion of task is written in hard disk.
     * @param taskList TaskList where task is deleted.
     * @param ui Ui that shows success/error messages from the deletion action.
     */
    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        try {
            ui.showSuccess(taskList.deleteTask(storage, this.input));
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Returns string success/error information after execution of
     * deleting of task in TaskList.
     *
     * @param storage Storage where deleting of task is written in hard disk.
     * @param taskList TaskList where task is deleted.
     * @param ui Ui that shows success/error messages from the deleting action.
     * @return String success/error information
     */
    @Override
    public String executeToString(Storage storage, TaskList taskList, Ui ui) {
        try {
            return taskList.deleteTask(storage, this.input);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
