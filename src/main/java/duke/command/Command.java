package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.DukeException;

/**
 * Represents a command in the Duke program.
 */
public abstract class Command {

    /**
     * Checks whether the program is quitting.
     *
     * @return false.
     */
    public boolean isQuitting() {
        return false;
    }

    /**
     * Executes the command.
     *
     * @param tasks TaskList of the program.
     * @param ui user interface of the program.
     * @param storage storage of the program.
     * @throws DukeException if a task doesn't exist or if there is an saving issue.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
