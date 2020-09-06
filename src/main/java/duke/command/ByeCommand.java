package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class ByeCommand extends Command {
    @ Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        return ui.showBye();
    }

    /**
     * Indicates a termination of the program
     * @return a false boolean value to break the loop
     */
    @Override
    public boolean isExit() {
        return true;
    }

}
