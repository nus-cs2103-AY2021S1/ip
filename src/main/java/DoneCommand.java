/**
 * Represents a done command for marking a task as done.
 */
public class DoneCommand extends Command {

    int taskNumber;

    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the done command to mark a task as done.
     * @param tasks list of tasks.
     * @param ui user interface to display done message.
     * @param storage file storage.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markTaskAsDone(taskNumber);
        Task currTask = tasks.getTask(taskNumber);
        return ui.showDoneMessage(currTask);
    }
}
