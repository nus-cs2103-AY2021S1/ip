package duke.command;

import duke.exception.DuplicateTaskException;
import duke.exception.FileUpdateFailException;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Handles the addition of tasks into the TaskList and updating of storage.
 */
public abstract class AddCommand extends Command {

    /**
     * Adds the new {@code Task} into the {@code TaskList} and updates the storage.
     *
     * @param newTask New task to be added.
     * @param taskList TaskList object.
     * @param ui User Interface object.
     * @param storage Storage object.
     * @return Response message to user.
     * @throws DuplicateTaskException If the new task to be added already exists in the current {@code TaskList}.
     * @throws FileUpdateFailException If storage file fails to get updated.
     */
    protected String addTask(Task newTask, TaskList taskList, Ui ui, Storage storage) throws DuplicateTaskException,
        FileUpdateFailException {
        if (taskList.contains(newTask)) {
            throw new DuplicateTaskException();
        }
        taskList.add(newTask);
        storage.updateFile(taskList);
        return ui.addTask(newTask, taskList.size());
    }
}
