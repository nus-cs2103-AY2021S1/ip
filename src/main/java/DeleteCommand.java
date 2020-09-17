/**
 * Class to run the delete command.
 */
public class DeleteCommand implements Command {

    protected final int TASK_NUMBER;

    /**
     * constructor
     */
    public DeleteCommand(int taskNumber) {
        this.TASK_NUMBER = taskNumber;
    }

    /**
     * Executes the delete command, causing Duke to delete a numbered task from the taskList,
     * provided that the number provided is valid.
     *
     * @param taskList Used by Duke to keep track of tasks.
     * @param ui Responsible for printing to console after execution.
     * @param storage Stores tasks in a text format.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task t = taskList.TASKS.get(TASK_NUMBER);
        taskList.deleteTask(TASK_NUMBER);
        storage.write(taskList.TASKS);
        ui.showLine();
        ui.deleteCommandSuccessMessage(TASK_NUMBER + 1, t);
        ui.showLine();
    }

    /**
     * Returns true if a bye command is called.
     * Returns False otherwise.
     *
     * @return boolean indicating whether Duke is to stop running.
     */
    public boolean isExit() {
        return false;
    }
}
