package duke.command;

import duke.TaskList;
import duke.Storage;
import duke.Ui;

/**
 * The ByeCommand class contains methods pertaining to the ByeCommand.
 *
 *  @author  Yen Pin Hsuan
 *  @version 1.0
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
