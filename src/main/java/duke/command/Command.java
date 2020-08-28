package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents the base command which all other command have to abide to.
 */
abstract public class Command {
    /**
     * Contains a series of logic for the execution of Command specified by the user.
     * 
     * @param taskItems represents the tasks which is added to the task list.
     * @param ui ui component which user interacts with or sees.
     * @param storage Object for saving and loading tasks list to hard disk.
     * @throws DukeException
     */
    public abstract void execute(TaskList taskItems, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns instruction to Duke class whether to terminate program.
     * 
     * @return bool value to terminate or not terminate the program.
     */
    public abstract boolean isExit();
}
