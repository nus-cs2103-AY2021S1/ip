package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command executed when user inputs "bye"
 */
public class ByeCommand extends Command {

    @Override
    public void executeCommand (Ui ui, Storage storage, TaskList taskList) {
        ui.byeMessage();
    }
}
