package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    protected String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        try {
            ui.showSuccess(taskList.deleteTask(storage, this.input));
        } catch(DukeException e) {
            ui.showError(e.getMessage());
        }
    }
}
