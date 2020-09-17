package bob.command;

import bob.exception.BobIOException;
import bob.exception.BobIndexOutOfBoundsException;
import bob.Storage;
import bob.TaskList;
import bob.UI;

/**
 * This command when executed marks a task of a particular index on the TaskList as done and calls
 * the UI class and Storage class to print out the appropriate messages
 * and update stored data respectively.
 */
public class DoneCommand extends Command {

    /** The index of the task to be marked as done on the TaskList */
    private int index;

    /** Constructs a DoneCommand by assigning a provided integer to the index parameter. */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to mark the task as done on the TaskList and update the Storage data accordingly.
     * It also calls on the provided UI to print out the appropriate messages.
     *
     * @param tasks the TaskList consisting of all tasks tracked by Bob.
     * @param ui the UI which prints out all messages corresponding to the Command.
     * @param storage the Storage which manages all saved data to be updated.
     * @throws BobIOException if Storage's text file does not exist.
     * @throws BobIndexOutOfBoundsException if the index of the task to be marked as done does not exist on TaskList.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws BobIOException, BobIndexOutOfBoundsException {
        assert tasks != null : "A tasklist should be provided";
        assert storage != null : "Storage should be provided";
        assert ui != null : "A UI should be provided";

        if (index == -1) {
            tasks.markAllDone();
            storage.updateSave(tasks);
            return ui.markAllDone(tasks);
        } else {
            tasks.get(index).markAsDone();
            storage.updateSave(tasks);
            return ui.markAsDone(tasks, index);
        }
    }
}
