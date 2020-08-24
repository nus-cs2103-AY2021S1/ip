package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class AddCommand extends Command {
    protected String input;
    protected String[] inputWords;

    public AddCommand(String input, String[] inputWords) {
        this.input = input;
        this.inputWords = inputWords;
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        try {
            ui.showSuccess(taskList.addTask(storage, this.input, this.inputWords));
        } catch(DukeException e) {
            ui.showError(e.getMessage());
        }
    }
}
