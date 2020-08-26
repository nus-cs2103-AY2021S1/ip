package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/** This class represents the command of deleting a task. */
public class DeleteCommand extends Command {
    private int i;

    /**
     * Constructs a DeleteCommand object associated with an int i.
     * @param i The index of the Task to be deleted in the TaskList.
     */
    public DeleteCommand(int i) {
        this.i = i;
    }

    /**
     * Delete the task at index i from the TaskList.
     * Print the deleted task.
     * Refresh the storage to reflect the changes.
     * @param taskList The TaskList to delete the task from.
     * @param ui The Ui that prints the delete message.
     * @param storage The Storage to delete the task from.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            ui.printDelete(taskList.delete(i));
            storage.refresh(taskList);
        } catch(IOException e) {
            System.out.println("Sorry something went wrong!");
        }
    }
}
