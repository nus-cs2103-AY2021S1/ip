package duke.command;

import duke.exception.DukeException;
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
     * Marks the task as done.
     * Prints the mark as done message to the user.
     * Refreshes the storage to reflect the changes.
     *
     * @param taskList The TaskList to get the task to be marked as done from.
     * @param ui The Ui that saves messages to be sent to the user..
     * @param storage The Storage to save the changes.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException {
        if (i > taskList.size()) {
            throw new DukeException("You only have " + taskList.size() + " tasks!");
        }
        ui.saveDoneMessage(taskList.setDone(i));
        storage.refresh(taskList);
    }
}
