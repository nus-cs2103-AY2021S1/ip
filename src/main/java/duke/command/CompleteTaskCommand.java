package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

/**
 * Encapsulates a command to complete a task.
 */
public class CompleteTaskCommand extends Command {

    /** Index of the task to complete in the current task list */
    final int taskIndex;

    /**
     * Constructor
     *
     * @param taskIndex Index of the task
     */
    public CompleteTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command to complete the task.
     *
     * @param storage Storage
     * @param tasks Task list
     * @param ui Ui
     * @return Output strings displayed in the UI showing task completion
     */
    @Override
    public String[] execute(Storage storage, TaskList tasks, Ui ui) {
        Task task = tasks.completeTaskAt(taskIndex);
        if (task == null) {
            return ui.getInvalidTaskIndexStrings();
        }
        return ui.getCompleteTaskStrings(task);
    }
}
