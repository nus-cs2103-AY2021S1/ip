package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command that can be interpreted by the system
 */
public abstract class Command {

    /**
     * Executes the command
     * @param tasks Current list of tasks
     * @param ui User interaction object
     * @param storage Storage object for saving the list of tasks
     * @throws DukeException Exception handling duke-related matters
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns if the command is an exit command terminating the session
     * @return false if it is not an exit command, true if it is an exit command
     */
    public boolean isExitCommand() {
        return false;
    }
}
