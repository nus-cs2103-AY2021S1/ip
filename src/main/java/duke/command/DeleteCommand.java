package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Class to delete a Task from the TaskList.
 */
public class DeleteCommand extends Command {
    private int taskIndex;

    /**
     * Creates a DeleteCommand with a given TaskIndex.
     *
     * @param taskIndex Index of Task in the TaskList to be deleted.
     */
    public DeleteCommand(int taskIndex) {
        super();
        this.taskIndex = taskIndex;
    }

    /**
     * Deletes the Task with corresponding TaskIndex.
     * Displays Task deleted message to user.
     *
     * @param ui Ui object to display messages to user.
     * @param storage Storage object to store items in the TaskList.
     * @param tasks Current list of Tasks.
     * @return Ui message to indicate Task has been deleted.
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList tasks) throws DukeException {
        if (tasks.numTask() == 0) {
            throw new DukeException("Hey! Your list is empty!");
        }

        if (this.taskIndex > tasks.numTask() || this.taskIndex <= 0) {
            throw new DukeException("Hey, no task with this number!");
        }

        Task taskToRemove = tasks.get(this.taskIndex - 1);
        tasks.removeTask(this.taskIndex - 1);
        storage.saveData(tasks.getTasks());
        return ui.taskDeleted(taskToRemove, tasks);
    }

}
