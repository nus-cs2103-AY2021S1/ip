package duke.command;

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
     * Creates DeleteCommand with a given TaskIndex.
     *
     * @param taskIndex Index of Task in the TaskList to be deleted.
     */
    public DeleteCommand(int taskIndex) {
        super();
        this.taskIndex = taskIndex;
    }

    /**
     * Deletes the Task with corresponding TaskIndex.
     * Displays task deleted message to user.
     *
     * @param ui Ui object to print messages to user.
     * @param storage Storage object to store items in the TaskList.
     * @param tasks Current list of tasks.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) {
        Task taskToRemove = tasks.get(this.taskIndex - 1);
        tasks.removeTask(this.taskIndex - 1);
        storage.saveData(tasks.getTasks());
        ui.taskDeleted(taskToRemove, tasks);
    }

}
