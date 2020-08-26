package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents the UnknownCommand when users enter unknown input.
 */
public class UnknownCommand extends Command {

    /**
     * Creates an UnknownCommand.
     */
    public UnknownCommand() { }

    /**
     * Replies user that the input is not being understood.
     *
     * @param tasks taskList that stores Task objects
     * @param ui Ui that handles input and output to User
     * @param storage storage storage that handles data storage
     * @throws DukeException DukeException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new DukeException("I'm sorry, but I don't know what that means!");
    }

    /**
     * Returns true if and only if it is a command to exit the program.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
