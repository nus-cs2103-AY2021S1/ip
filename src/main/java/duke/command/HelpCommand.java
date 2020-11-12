package duke.command;

import duke.exception.DukeException;
import duke.task.TaskManager;
import duke.ui.Ui;

/**
 * <p>duke.command.HelpCommand class defines the behaviour of a command to give user a list of possible commands.</p>
 */
public class HelpCommand extends Command {
    @Override
    public void execute(TaskManager taskManager, Ui ui) throws DukeException {
        ui.replyHelp();
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
