package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param taskList A TaskList instance.
     * @param ui A Ui instance.
     * @param storage A storage instance.
     * @throws DukeException if the command cannot be executed.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns a boolean indicating the program exit status.
     *
     * @return true if the program should exit after executing this command.
     */
    public boolean isExit() {
        return false;
    }
}
