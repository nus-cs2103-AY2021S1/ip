package duke.command;

import duke.error.DukeError;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.UI;

public class HelpCommand implements Command {

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeError {
        ui.showHelp();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
