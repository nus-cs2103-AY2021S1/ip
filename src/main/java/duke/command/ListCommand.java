package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.dukeexception.DukeException;

/**
 * Command that displays the user's list of tasks when executed.
 */
public class ListCommand extends Command {

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        return ui.returnListReply(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
