package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * The AddCommand class implements a AddCommand command
 * executable to add a task to the task list.
 *
 * @author Amy Lim Zhi Ting
 * @version v0.1
 */
public class AddCommand extends Command {
    protected String input;

    public AddCommand(String input) {
        this.input = input;
    }

    /**
     * Executes adding of task in TaskList and shows success/error information.
     *
     * @param storage Storage where adding of task is written in hard disk.
     * @param taskList TaskList where task is added.
     * @param ui Ui that shows success/error messages from the adding action.
     */
    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        try {
            ui.showSuccess(taskList.addTask(storage, this.input));
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Returns string success/error information after execution of
     * adding of task in TaskList.
     *
     * @param storage Storage where adding of task is written in hard disk.
     * @param taskList TaskList where task is added.
     * @param ui Ui that shows success/error messages from the adding action.
     * @return String success/error information
     */
    @Override
    public String executeToString(Storage storage, TaskList taskList, Ui ui) {
        try {
            return taskList.addTask(storage, this.input);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
