package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.UI;

public class HelpCommand implements Command {

    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) {
        return UI.HELP_MESSAGE;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof HelpCommand) {
            return true;
        } else {
            return true;
        }
    }

}
