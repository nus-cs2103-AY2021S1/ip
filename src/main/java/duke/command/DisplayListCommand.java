package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.UI;

public class DisplayListCommand implements Command {

    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) {
        return ui.displayList(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof DisplayListCommand) {
            return true;
        } else {
            return true;
        }
    }

}
