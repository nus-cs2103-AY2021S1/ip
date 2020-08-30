package duke.command;

import duke.TaskList;
import duke.Storage;
import duke.Ui;

/**
 * The ByeCommand class contains methods pertaining to the ByeCommand.
 */
public class ByeCommand extends Command {

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        ui.bye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
