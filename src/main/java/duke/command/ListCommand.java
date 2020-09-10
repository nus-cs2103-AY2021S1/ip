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
    public void execute(TaskList tasks, Storage storage) {
        System.out.println("Na, here is your list lah:" + tasks.toString());
    }

    @Override
    public String executeToGui(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        return ui.returnReply("Na, here is your list lah:" + tasks.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
