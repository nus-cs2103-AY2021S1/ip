package command;

import exception.DukeException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class InvalidCommand extends Command{
    public InvalidCommand() {}

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        throw new DukeException(ui.LINE + "Invalid command! \n" + ui.LINE);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
