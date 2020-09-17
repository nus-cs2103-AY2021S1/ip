package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Creates a command object.
 */
public abstract class Command {
    /**
     * Executes the command.
     * 
     * @param tasks The list of existing tasks.
     * @param ui The ui that handles user interaction.
     * @param storage The storage that stores the list of existing tasks.
     * @throws DukeException when the command cannot be executed.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Determines whether the command is an exit command.
     * 
     * @return The status of the command.
     */
    public abstract boolean isExit();
    
}
