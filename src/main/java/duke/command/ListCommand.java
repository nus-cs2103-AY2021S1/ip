package duke.command;

import duke.TaskList;
import duke.Storage;
import duke.Ui;

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
