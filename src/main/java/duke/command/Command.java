package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command class to execute commands.
 */
public abstract class Command {

    /**
     * Executes commands, depending on child classes.
     * @param tasks TaskList that is being executed on.
     * @param ui Ui to update the user.
     * @param storage Storage to update the text file.
     * @throws DukeException If there is errors.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Checks if it is the exit command.
     * @return Returns true if it is the exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
