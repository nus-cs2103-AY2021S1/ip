package duke.command;

import duke.common.CustomException;
import duke.common.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

public class ListCommand extends Command {
    public void execute (TaskList tasks, Ui ui, Storage storage) throws CustomException {
        Ui.displayTasks(tasks.getList());
    }
}
