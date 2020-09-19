package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {
    public ListCommand() {
        super();
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) {
        ui.listTasks(tasks);
    }

}
