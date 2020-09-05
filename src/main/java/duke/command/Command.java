package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates a command that will be sent to the chat bot.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param taskList          The list of tasks known by the chat bot.
     * @param ui                The Ui that is used by the chat bot.
     * @param storage           The storage used by the chat bot.
     * @return chat bot reply message.
     * @throws DukeException    If the execution fails at any step.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns a flag which will end the while loop and terminate the bot.
     *
     * @return <code>false</code> by default.
     */
    public boolean isExit() {
        return false;
    }
}
