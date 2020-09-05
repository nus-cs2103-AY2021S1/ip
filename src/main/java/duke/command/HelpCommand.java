package duke.command;

import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;

/**
 * The help command provides users a way to learn how to use the program.
 * Available commands will be listed out for the user.
 */
public class HelpCommand implements Command {

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.getListOfCommands();
    }
}
