package duke.command;

import duke.*;

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
     * Executes a command with the given tasks, ui and storage.
     * @param tasks is the task list that the command will execute with.
     * @param ui is the ui that the command will execute with.
     * @param storage is the storage that the command will execute with.
     * @throws DukeException when there is a problem executing the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Indicates whether the command is an exit command.
     * @return false by default and only true if the command is an exit command.
     */
    public boolean isExit() {
        return false;
    }
}