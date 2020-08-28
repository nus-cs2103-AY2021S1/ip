package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

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
}
