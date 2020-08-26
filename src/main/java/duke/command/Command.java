package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

/**
 * Command is an abstract class that encapsulates a command to be executed by Duke.
 */
public abstract class Command {
    /**
     * Executes the command.
     * @param storage Storage where data will be uploaded and retrieved to and from.
     * @param taskList List of tasks to be manipulated.
     * @throws DukeException
     */
    public abstract void execute(Storage storage, TaskList taskList) throws DukeException;
}
