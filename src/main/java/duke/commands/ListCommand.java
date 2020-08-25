package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.formatLst(tasks.lst);
    }
}
