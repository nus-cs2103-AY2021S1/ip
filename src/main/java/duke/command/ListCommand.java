package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

/**
 * The ListCommand class contains methods pertaining to the ListCommand.
 */
public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        ui.print(taskList.list());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
