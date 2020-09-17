package bob.command;

import bob.exception.BobException;
import bob.Storage;
import bob.TaskList;
import bob.UI;

/**
 * This command indicates that Bob may terminate.
 */
public class ExitCommand extends Command {

    /**
     * Returns the exit message.
     *
     * @param tasks the TaskList consisting of all tasks tracked by Bob.
     * @param ui the UI which prints out all messages corresponding to the Command.
     * @param storage the Storage which manages all saved data to be updated.
     * @return the message provided by the UI.
     * @throws BobException
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) {
        return ui.exit();
    }

    /**
     * Returns true to indicate that Bob may terminate.
     *
     * @return true.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
