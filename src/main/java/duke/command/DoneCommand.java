package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * This class represents the command of marking a task as done
 */
public class DoneCommand extends Command {
    private int i;

    /**
     * Constructs a DoneCommand object associated with an int i
     * @param i The index of the Task to be marked as done in the TaskList
     */
    public DoneCommand(int i) {
        this.i = i;
    }

    /**
     * Mark the task as done.
     * Print the mark as done message to the user.
     * Refresh the storage to reflect the changes.
     * @param taskList The TaskList to get the task to be marked as done from
     * @param ui The Ui to print the mark as done message to the user
     * @param storage The Storage to save the changes
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            ui.printDone(taskList.setDone(i));
            storage.refresh(taskList);
        } catch (IOException e) {
            System.out.println("Sorry something went wrong!");
        }
    }
}
