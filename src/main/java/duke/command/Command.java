package duke.command;

import duke.exception.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/** A command for the Duke bot to execute. */
public abstract class Command {

    /** The type of command. */
    protected boolean isExit;

    /**
     * Executes the command accordingly.
     *
     * @param taskList The task list that stores and modifies the list of saved tasks.
     * @param ui The UI of the bot.
     * @param storage The storage system of the bot.
     * @throws DukeException If there is something wrong with the task input.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns the type of command.
     *
     * @return True if the command is an @ExitCommand.
     */
    public boolean isExit() {
        return isExit;
    }

}
