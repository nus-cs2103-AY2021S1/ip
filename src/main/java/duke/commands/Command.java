package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a general command/command category.
 * @version 1.0
 */
public abstract class Command {
    protected String commandName;
    protected boolean isExit;

    /**
     * Returns if the command exterminate the chat bot.
     *
     * @return A boolean representing if the command exterminate the chat bot.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Executes the instructions of the command.
     *
     * @param list A TaskList object of which the command is executed on.
     * @param ui An UI object to interact with the user if required by the command.
     * @param storage A Storage object to write/access information to/from a file if required by the command.
     * @throws DukeException if any exception is thrown during execution in the format of DukeException.
     */
    public abstract void execute(TaskList list, Ui ui, Storage storage) throws DukeException;
}
