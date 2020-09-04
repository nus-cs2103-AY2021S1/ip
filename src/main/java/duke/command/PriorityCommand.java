package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * The PriorityCommand class implements a PriorityCommand command executable
 * to set the priority of a task.
 *
 * @author Amy Lim Zhi Ting
 * @version v0.1
 */
public class PriorityCommand extends Command {
    protected String input;

    public PriorityCommand(String input) {
        this.input = input;
    }

    /**
     * Executes marking done of task in TaskList and
     * shows success/error information.
     *
     * @param storage Storage where marking done of task is written in hard disk.
     * @param taskList TaskList where task is marked done.
     * @param ui Ui that shows success/error messages from the mark done action.
     */
    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        try {
            ui.showSuccess(taskList.setTaskPriority(storage, this.input));
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Returns string success/error information after execution of
     * marking done of task in TaskList.
     *
     * @param storage Storage where marking done of task is written in hard disk.
     * @param taskList TaskList where task is marked done.
     * @param ui Ui that shows success/error messages from the marking done action.
     * @return String success/error information
     */
    @Override
    public String executeToString(Storage storage, TaskList taskList, Ui ui) {
        try {
            return taskList.setTaskPriority(storage, this.input);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
