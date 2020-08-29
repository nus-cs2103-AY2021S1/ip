package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Encapsulates a command that will be issued to the chat bot.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks   The list of tasks known by the chat bot.
     * @param storage The storage that is used by the chat bot.
     * @return A string detailing the outcome of the execution.
     * @throws DukeException If the execution fails at any step.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws DukeException;

    /**
     * Returns a flag that indicates whether the command is one which should result in an exit of
     * the program.
     *
     * @return <code>false</code> by default.
     */
    public boolean isExit() {
        return false;
    }
}
