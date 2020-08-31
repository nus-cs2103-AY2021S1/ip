package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.tasks.TaskList;

/**
 * Command to end program with "bye".
 */
public class ByeCommand extends Command {

    /**
     * Prints out exit message to send of user.
     *
     * @param tasklist list of all the tasks stored in Duke so far.
     * @param storage stores all the tasks being added so far into user's local storage.
     * @throws DukeException throw when error occurs
     */
    public String execute(TaskList tasklist, Storage storage) throws DukeException {
        return ("Bye. Hope to see you again soon!");
    }

    public boolean isExit() {
        return true;
    }
}
