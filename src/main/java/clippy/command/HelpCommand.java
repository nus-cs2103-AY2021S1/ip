package clippy.command;

import clippy.exception.ClippyException;
import clippy.storage.Storage;
import clippy.task.TaskList;
import clippy.ui.Ui;

public class HelpCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ClippyException {
        return ui.showHelp();
    }

}
