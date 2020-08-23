package duke.commands;

import duke.commands.Command;
import duke.tasks.TaskList;
import duke.storage.Storage;
import duke.ui.UI;

public class InvalidCommand extends Command {
    private final String message = "Invalid command/format! check --help for more info";

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        ui.showError(message);
    }
}
