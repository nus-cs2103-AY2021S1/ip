package duke.command;

import duke.exception.FileUpdateFailException;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Handles the addition of tasks into the Task List and updating of storage.
 */
public abstract class AddCommand extends Command {

    /**
     * Adds the new {@code Task} into the {@code TaskList} and updates the storage.
     *
     * @param newTask New task to be added.
     * @param tasks Task List object.
     * @param ui User Interface object.
     * @param storage Storage object.
     * @throws FileUpdateFailException If storage file fails to get updated.
     */
    protected String addTask(Task newTask, TaskList tasks, Ui ui, Storage storage) throws FileUpdateFailException {
        tasks.add(newTask);
        storage.updateFile(tasks);
        return ui.addTask(newTask, tasks.size());
    }
}
