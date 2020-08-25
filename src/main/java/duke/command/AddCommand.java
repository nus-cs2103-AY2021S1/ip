package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class AddCommand extends Command {
    protected String input;
    protected String[] inputWords;

    public AddCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        try {
            ui.showSuccess(taskList.addTask(storage, this.input));
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }
}
