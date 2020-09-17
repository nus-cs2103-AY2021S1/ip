package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

/**
 * Represents a command that the user gives.
 */
public abstract class Command {
    /**
     * Creates a new command.
     */
    public Command() {
    }

    /**
     * Executes a command with the given tasks and storage.
     *
     * @param tasks is the task list that the command will execute with.
     * @param storage is the storage that the command will execute with.
     * @return a command response after executing the command.
     * @throws DukeException when there is a problem executing the command.
     */
    public abstract CommandResponse execute(TaskList tasks, Storage storage) throws DukeException;

    /**
     * Indicates whether the command is an exit command.
     *
     * @return false by default and only true if the command is an exit command.
     */
    public boolean isExit() {
        return false;
    }
}
