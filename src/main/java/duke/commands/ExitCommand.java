package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class ExitCommand extends Command {
    public ExitCommand() {
        this.commandName = "Exit";
        this.isExit = true;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.showFarewell();
    }
}
