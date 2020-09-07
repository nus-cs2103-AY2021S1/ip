package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

/**
 * Encapsulates a command to complete a task
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
     * Executes the command to complete the task
     *
     * @param storage Storage
     * @param tasks Task list
     * @param ui Ui
     * @return Output strings
     */
    @Override
    public String[] execute(Storage storage, TaskList tasks, Ui ui) {
        assert storage != null;
        assert tasks != null;
        assert ui != null;

        Task task = tasks.completeTaskAt(this.taskIndex);
        if (task != null) {
            return ui.getCompleteTaskStrings(task);
        } else {
            return ui.getInvalidTaskIndexStrings();
        }
    }
}
