package duke;


/**
 * Supports deleting of tasks from the TaskList.
 */
public class DeleteCommand extends Command {
    int taskIdx;

    /**
     * Instantiates DeleteCommand.
     *
     * @param taskIdx Index of task to be deleted.
     */
    DeleteCommand(int taskIdx) {
        this.taskIdx = taskIdx;

    }

    /**
     * Executes command by deleting required task from TaskList
     * and rewrite data to storage.
     *
     * @param tasks TaskList containing the tasks.
     * @param storage To read and write to file.
     * @param ui Interact with user.
     * @throws DukeException If input does not meet requirements.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        Task task = tasks.deleteTask(taskIdx);
        ui.showDeleteTask(task, tasks.getSize());
        storage.saveTasksToFile(tasks);
    }

}
