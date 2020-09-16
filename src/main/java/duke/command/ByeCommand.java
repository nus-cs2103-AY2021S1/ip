package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.UI;

public class ByeCommand implements Command {

    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) {
        storage.saveData(ui, taskList);
        return UI.GOODBYE_MESSAGE;
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof ByeCommand) {
            return true;
        } else {
            return true;
        }
    }

}
