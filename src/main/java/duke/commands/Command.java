package duke.commands;

import duke.exceptions.DukeException;
import duke.support.Storage;
import duke.task.TaskList;

/**
 * Represents the command the system will run.
 */
public abstract class Command {

    /**
     * Runs the operation inside each command.
     *
     * @param taskList A {@code TaskList} where stores all tasks.
     * @param storage A {@code Storage} where reads and writes data file.
     * @return A String as a repsonse to the user's command.
     */
    public abstract String run(TaskList taskList, Storage storage) throws DukeException;
    public boolean isBye() {
        return false;
    }

}
