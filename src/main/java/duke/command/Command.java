package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Encapsulates a command that will be issued to the chatbot.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks   The list of tasks known by the chatbot.
     * @param ui      The UI that is used by the chatbot.
     * @param storage The storage that is used by the chatbot.
     * @throws DukeException If the execution fails at any step.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

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
