package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * A command based on the user input that will be sent to Duke.
 */
public abstract class Command {

    /**
     * Abstract method to be implemented by Command subclasses, execution will vary depending on the type of Command.
     *
     * @param taskList the List containing all the tasks that Duke has stored.
     * @param ui a Ui object for interaction with users.
     * @param storage the database for Duke to save all tasks to the user's local storage.
     * @throws DukeException when the type of task being added is unknown.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Whether or not Duke program should terminate.
     *
     * @return true only when the command is bye.
     */
    public boolean isExit() {
        return false;
    }
}
