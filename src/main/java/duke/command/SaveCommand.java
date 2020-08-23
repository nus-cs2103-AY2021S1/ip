package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.UI;

public class SaveCommand implements Command {
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        storage.saveData(ui, taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
