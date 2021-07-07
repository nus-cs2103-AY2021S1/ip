package duke.command;

import duke.exception.DukeException;
import duke.note.NoteList;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a Command to delete an existing object in the TaskList
 */
public class DeleteCommand extends Command {


    private final String markItem;

    /**
     * Constructs a command with a String value of the instruction to be further processed.
     *
     * @param secondArg String argument to specify the Task to delete.
     */
    public DeleteCommand(String secondArg) {
        markItem = secondArg;
    }

    /**
     * Processes the String attribute markItem to make sure that a valid Task number
     * is given. The Task will be deleted from the TaskList if it is valid. Otherwise a
     * DukeException will be thrown. The Storage is updated with this deletion and a relevant
     * String message to notify the user on this deletion or exception will be returned.
     *
     * @param tasks   TaskList object containing the list of tasks.
     * @param notes   NoteList object containing the list of notes.
     * @param ui      Ui object to output messages to the user.
     * @param taskStorage Storage object for storing tasks.
     * @param noteStorage Storage object for storing notes.
     * @return String response to user.
     * @throws DukeException If number is not a valid item number in the task list.
     */
    @Override
    public String execute(TaskList tasks, NoteList notes, Ui ui, Storage taskStorage, Storage noteStorage)
            throws DukeException {

        //checks if second argument of instruction is valid
        try {
            boolean isAboveZero = (Integer.parseInt(this.markItem) < 1);
            boolean isBelowListSize = (Integer.parseInt(this.markItem) > tasks.getSize());
            if (isAboveZero || isBelowListSize) {
                throw new DukeException("Please enter a valid item number from the list! Type 'list' to check"
                        + " your task list.");
            }
        } catch (NumberFormatException e) { //second argument wrong format
            throw new DukeException("Please only input 'delete <item number>' with no other inputs!");
        }
        int deleteIndex = Integer.parseInt(this.markItem);

        //delete task
        Task deletedItem = tasks.delete(deleteIndex);

        //print output
        String output = ui.printTaskDeleted(tasks, deletedItem);

        //update storage
        taskStorage.saveListToHardDisk(tasks);

        return output;
    }

    /**
     * Returns false to indicate that the Command does not exit the program.
     *
     * @return Exit program indicator.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}


