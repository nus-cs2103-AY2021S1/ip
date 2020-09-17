package bob.command;

import bob.exception.BobIOException;
import bob.exception.BobIndexOutOfBoundsException;
import bob.Storage;
import bob.TaskList;
import bob.UI;

/**
 * This command when executed deletes a task of a particular index from the TaskList and calls
 * the UI class and Storage class to print out the appropriate messages
 * and update stored data respectively.
 */
public class DeleteCommand extends Command {

    /** The index of the task to be deleted from the TaskList */
    private int index;

    /** Constructs a DeleteCommand by assigning a provided integer to the index parameter. */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to delete the task from the TaskList and update the Storage data accordingly.
     * It also calls on the provided UI to print out the appropriate messages.
     *
     * @param tasks the TaskList consisting of all tasks tracked by Bob.
     * @param ui the UI which prints out all messages corresponding to the Command.
     * @param storage the Storage which manages all saved data to be updated.
     * @throws BobIOException if Storage's text file does not exist.
     * @throws BobIndexOutOfBoundsException if the index of the task to be deleted does not exist on TaskList.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws BobIOException, BobIndexOutOfBoundsException {
        assert tasks != null : "A tasklist should be provided";
        assert storage != null : "Storage should be provided";
        assert ui != null : "A UI should be provided";


        if (index == -1) {
            String message = ui.deleteAll(tasks);
            tasks.deleteAll();
            storage.updateSave(tasks);
            return message;
        } else {
            String message = ui.deleteTask(tasks, index);
            tasks.delete(index);
            storage.updateSave(tasks);
            return message;
        }
    }

}
