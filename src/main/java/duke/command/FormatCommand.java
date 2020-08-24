package duke.command;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class FormatCommand extends Command {
    public FormatCommand() {
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        ui.showFormat();
    }
}
