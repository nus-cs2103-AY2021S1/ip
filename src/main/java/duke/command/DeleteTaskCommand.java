package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import duke.task.Task;

/**
 * Encapsulates a command to delete a task
 */
public class DeleteTaskCommand extends Command {

    /** Index of the task to be deleted in the current task list */
    final int taskIndex;

    /**
     * Constructor
     *
     * @param taskIndex Index of the task
     */
    public DeleteTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command to delete the task.
     *
     * @param storage Storage
     * @param taskList Task list
     * @param archive Archive
     * @param ui Ui
     * @return Output strings displayed on the UI showing task deletion
     */
    @Override
    public String[] execute(Storage storage, TaskList taskList, TaskList archive, Ui ui) {
        assert taskList != null;
        assert ui != null;

        Task task = taskList.deleteTaskAt(taskIndex);
        if (task == null) {
            return ui.getInvalidTaskIndexStrings();
        }
        return ui.getDeleteTaskStrings(taskList, task);
    }
}
