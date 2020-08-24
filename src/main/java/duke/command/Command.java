package duke.command;

import duke.*;

/**
 * Represents a command from the user.
 */
public abstract class Command {

    /**
     * Creates a new Command.
     */
    public Command() {
    }

    /**
     * Executes the given command with the specified tasks, ui and storage.
     * @param tasks The tasks the command is executed with.
     * @param ui The ui the command is executed with.
     * @param storage The storage the command is executed with.
     * @throws DukeException If there was a problem with executing the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Indicates if this command is an exit command.
     * @return False by default, unless this is an exit command.
     */
    public boolean isExit() {
        return false;
    }
}
