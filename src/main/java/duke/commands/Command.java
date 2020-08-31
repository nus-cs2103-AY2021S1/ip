package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.tasks.TaskList;

/**
 * Command sent to Duke from user input.
 */
public abstract class Command {

    /**
     * Abstract method to be implemented by all Command subclasses, with each Command applying different executions.
     *
     * @param tasklist list of all the tasks stored in Duke so far.
     * @param storage stores all the tasks being added so far into user's local storage.
     * @throws DukeException throw when error occurs while executing commands
     */
    public abstract String execute(TaskList tasklist, Storage storage) throws DukeException;

    /**
     * Decides if program should terminate.
     *
     * @return true only if program is ending.
     */
    public boolean isExit() {
        return false;
    }
}
