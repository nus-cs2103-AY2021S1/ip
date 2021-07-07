package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class ArchiveTaskCommand extends Command {

    /** Index of the task in the main task list to be archived */
    private final int taskIndex;

    /**
     * Constructor
     *
     * @param taskIndex Index of the task in the main task list
     */
    public ArchiveTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command to archive the task.
     *
     * @param storage Storage
     * @param taskList Task list
     * @param archive Archive
     * @param ui Ui
     * @return Output strings displayed in the UI showing task archiving
     */
    @Override
    public String[] execute(Storage storage, TaskList taskList, TaskList archive, Ui ui) {
        assert taskList != null;
        assert archive != null;
        assert ui != null;

        Task task = taskList.deleteTaskAt(taskIndex);
        if (task == null) {
            return ui.getInvalidTaskIndexStrings();
        }
        archive.addTask(task);
        return ui.getArchiveTaskStrings(taskList, task);
    }
}
