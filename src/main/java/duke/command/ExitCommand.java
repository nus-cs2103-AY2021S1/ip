package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.dukeexception.DukeException;

/**
 * Command that displays a goodbye message and exits(quits) the bot when executed.
 */
public class ExitCommand extends Command {

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        return ui.returnExitReply();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
