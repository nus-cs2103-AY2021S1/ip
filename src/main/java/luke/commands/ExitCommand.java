package luke.commands;

import luke.Storage;
import luke.TaskList;
import luke.Ui;
import luke.exception.LukeException;

public class ExitCommand extends Command {

    public ExitCommand() {
        super();
    }

    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) throws LukeException {
        return ui.showExitResult();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
