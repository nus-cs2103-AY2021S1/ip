package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents an abstract command that is to be executed by Duke.
 */
public abstract class Command {


    /**
     * Executes the command based on the type of command it is.
     * @param taskList is the list of tasks stored by Duke.
     * @param ui is the user interface to read inputs from the user and print messages.
     * @param storage deals with saving tasks into the file and loading tasks
     *                from the file.
     *
     * @throws DukeException if the command to be executed is incorrect.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;


    /**
     * Indicates if Duke should still be running after the command is executed.
     * @return true if Duke should keep running and false if not.
     */
    public abstract boolean continueRunning();
}
