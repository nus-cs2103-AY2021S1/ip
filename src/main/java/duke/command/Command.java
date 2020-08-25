package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.TaskList;

/**
 * Represents an abstract Command from which other *Commands inherit.
 */
public abstract class Command {

    /**
     * Default constructor for Command class.
     */
    public Command() {
    }

    /**
     * Executes the command.
     *
     * @param tasks Tasklist containing current tasks.
     * @param ui Ui for displaying output.
     * @param storage Storage of tasks in hard disk.
     * @throws DukeException If command is invalid.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns whether a Command is an ExitCommand.
     *
     * @return False by default as most Commands are not ExitCommands.
     */
    public boolean isExit() {
        return false;
    }
}
