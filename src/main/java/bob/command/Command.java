package bob.command;

import bob.exception.BobException;
import bob.Storage;
import bob.TaskList;
import bob.UI;

/**
 * An abstraction of commands which may be executed by Bob.
 */
public abstract class Command {

    /**
     * Executes the command by updating the TaskList and Storage accordingly, and returning
     * the appropriate message from the UI.
     *
     * @param tasks the TaskList consisting of all tasks tracked by Bob.
     * @param ui the UI which prints out all messages corresponding to the Command.
     * @param storage the Storage which manages all saved data to be updated.
     * @return the message provided by the UI.
     * @throws BobException if any errors arise.
     */
    public abstract String execute(TaskList tasks, UI ui, Storage storage) throws BobException;

    /**
     * Returns a boolean value to indicate if Bob should stop accepting commands from the user.
     *
     * @return false to indicate that Bob should continue accepting commands.
     */
    public boolean isExit() {
        return false;
    }



}
