package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.UI;

public class ByeCommand implements Command {

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        storage.saveData(ui, taskList);
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
