package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.listTasksMessage(tasks);
    }
}
