package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.UI;

public class SaveCommand implements Command {
    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) {
        return storage.saveData(ui, taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
