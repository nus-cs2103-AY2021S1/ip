package duke.commands;

import duke.tasks.TaskList;
import duke.storage.Storage;
import duke.ui.UI;

public class Command {
    protected String commandDescription;
    private boolean isExit;


    public Command() {

    }

    public Command(String commandDescription, boolean isExit) {
        this.commandDescription = commandDescription;
        this.isExit = isExit;
    }

    public boolean isExit() {
        return this.isExit;
    }

    public void execute(TaskList taskList, UI ui, Storage storage) {
        ui.showError("Can't execute generic command");
    }
}
