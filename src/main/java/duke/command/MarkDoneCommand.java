package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class MarkDoneCommand extends Command {
    protected String input;

    public MarkDoneCommand(String input) {
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
            ui.showSuccess(taskList.markDone(storage, this.input));
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }
}
