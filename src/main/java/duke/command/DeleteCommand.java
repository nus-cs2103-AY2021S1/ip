package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/** This class represents the command of deleting a task. */
public class DeleteCommand extends Command {
    private int i;

    /**
     * Constructs a DeleteCommand object.
     *
     * @param i The index of the Task to be deleted in the TaskList.
     */
    public DeleteCommand(int i) {
        this.i = i;
    }

    /**
     * Deletes the task at index i from the TaskList.
     * Prints the deleted task.
     * Refreshes the storage to reflect the changes.
     *
     * @param taskList The TaskList to delete the task from.
     * @param ui The Ui that saves messages to be sent to the user.
     * @param storage The Storage to delete the task from.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException {
        if (i > taskList.size()) {
            throw new DukeException("You only have " + taskList.size() + " tasks!");
        }
        ui.saveDeleteMessage(taskList.delete(i));
        storage.refresh(taskList);
    }
}
