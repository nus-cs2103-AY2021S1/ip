public class DeleteCommand extends Command {

    protected int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        // check if the taskNumber provided is in range
        if (taskNumber > tasks.getNumTasks() || taskNumber <= 0) {
            throw new DukeException("Please enter a valid task number.");
        } else {
            ui.showDeleted(tasks.getTask(taskNumber), (tasks.getNumTasks() - 1));
            tasks.deleteTask(taskNumber); // delete from tasklist
            storage.overwriteFile(tasks.getTaskList()); // delete from storage
        }
    }

    public boolean isExit() {
        return false;
    }
}
