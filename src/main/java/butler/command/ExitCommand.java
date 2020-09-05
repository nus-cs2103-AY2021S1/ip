package butler.command;

import butler.exception.ButlerException;
import butler.io.Storage;
import butler.io.Ui;
import butler.task.TaskList;

public class ExitCommand extends Command {

    public ExitCommand() {
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws ButlerException {
        ui.showExit();
        storage.storeTaskList(taskList);
    }

    @Override
    public Boolean isExit() {
        return true;
    }
}
