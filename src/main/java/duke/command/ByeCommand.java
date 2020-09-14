package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/** This class represents the command to terminate the program. */
public class ByeCommand extends Command {

    /** Exits the program. */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.saveByeMessage();
    }
}
