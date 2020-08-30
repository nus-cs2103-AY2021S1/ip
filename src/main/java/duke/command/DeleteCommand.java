package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeInvalidIndexException;
import duke.task.Task;

import java.io.IOException;

/**
 * Represents a delete task action.
 */
public class DeleteCommand implements Command {
    private final int index; //0 to tasks.getSize() - 1

    /**
     * Class constructor with specified index.
     *
     * @param index The index of the Task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Performs the deleting of task from task list.
     *
     * @param tasks The TaskList to delete the task from.
     * @param ui The Ui to show responses or error messages.
     * @param storage The Storage to save the TaskList.
     * @return True because Duke should continue running.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.deleteTask(index);
            storage.save(tasks);
            ui.showDeleted(task, tasks.getSize());
        } catch (DukeInvalidIndexException e) {
            ui.showError(e);
        } catch (IOException e) {
            ui.showError(e);
        }
        return true;
    }
}
