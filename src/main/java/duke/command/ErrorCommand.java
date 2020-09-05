package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.UI;

public class ErrorCommand implements Command {
    private final Exception error;

    public ErrorCommand(Exception error) {
        this.error = error;
    }

    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) {
        return ui.showError(error.getMessage());
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
