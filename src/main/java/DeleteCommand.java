package main.java;

import java.io.IOException;

/**
 * This command when executed deletes a task of a particular index from the TaskList and calls
 * the UI class and Storage class to print out the appropriate messages
 * and update stored data respectively.
 */
public class DeleteCommand extends Command {

    /** The index of the task to be deleted from the TaskList */
    int index;

    /** Constructs a DeleteCommand by assigning a provided integer to the index parameter. */
    DeleteCommand(int index) {
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
    public void execute(TaskList tasks, UI ui, Storage storage) throws BobIOException, BobIndexOutOfBoundsException {
        ui.deleteTask(tasks,index);
        tasks.delete(index);
        storage.updateSave(tasks);
    }

}
