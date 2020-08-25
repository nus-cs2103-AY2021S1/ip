package duke.command;

import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

public class ByeCommand extends Command {

    @ Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.showBye();
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
