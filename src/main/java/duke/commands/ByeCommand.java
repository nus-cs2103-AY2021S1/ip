package duke.commands;

import duke.*;
import duke.tasks.TaskList;

/**
 * Command to end program with "bye".
 */
public class ByeCommand extends Command {

    /**
     * Prints out exit message to send of user.
     *
     * @param tasklist list of all the tasks stored in Duke so far.
     * @param ui prints out messages notifying user of what is being done.
     * @param storage stores all the tasks being added so far into user's local storage.
     * @throws DukeException
     */
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException {
        ui.showExit();
    }

    public boolean isExit() {
        return true;
    }
}
