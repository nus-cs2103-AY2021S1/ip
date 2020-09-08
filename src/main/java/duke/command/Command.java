package duke.command;

import duke.exception.DukeException;
import duke.storage.StateManager;
import duke.task.TaskList;

public abstract class Command {
    /**
     * A method to execute the command.
     *
     * @param tasks   The TaskList used by Duke.
     * @param stateManager The Storage used by Duke.
     * @return CommandResult object containing information for ui
     * @throws DukeException If a DukeException occurs during execution by child classes.
     */
    public abstract CommandResult execute(TaskList tasks, StateManager stateManager) throws DukeException;


    /**
     * Returns whether the command object is the ExitCommand.
     *
     * @return A boolean of whether the command is an ExitCommand and default set to false.
     */
    public boolean isExitCommand() {
        return false;
    }
}
