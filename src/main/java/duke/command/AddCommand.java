package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

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
        } catch(DukeException e) {
            ui.showError(e.getMessage());
        }
    }
}
