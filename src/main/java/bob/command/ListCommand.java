package bob.command;

import bob.exception.BobIndexOutOfBoundsException;
import bob.Storage;
import bob.TaskList;
import bob.UI;

/**
 * This command when executed prints out all tasks on a TaskList.
 */
public class ListCommand extends Command {

    /**
     * Executes the command by calling on the UI to return the contents of a TaskList in a message.
     *
     * @param tasks the TaskList consisting of all tasks tracked by Bob.
     * @param ui the UI which prints out all messages corresponding to the Command.
     * @param storage the Storage which manages all saved data to be updated.
     * @return the message provided by the UI.
     * @throws BobIndexOutOfBoundsException
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws BobIndexOutOfBoundsException {
        assert tasks != null : "A tasklist should be provided";
        return ui.printOutList(tasks);
    }
}
