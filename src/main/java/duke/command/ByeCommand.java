package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Command to close Duke Application.
 */
public class ByeCommand extends Command {
    public ByeCommand(){}

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return "Goodbye! See you soon!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
