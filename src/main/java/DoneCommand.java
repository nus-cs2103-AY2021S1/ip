public class DoneCommand extends Command {
    protected int taskNumber;

    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        // check if the taskNumber provided is in range
        if (taskNumber > tasks.getNumTasks() || taskNumber <= 0) {
            throw new DukeException("Please enter a valid task number.");
        } else if (tasks.getTask(taskNumber).isDone) {
            throw new DukeException("You have already marked this task as done!");
        } else {
            tasks.doneTask(taskNumber);
            storage.overwriteFile(tasks.getTaskList());
            ui.showMarkedDone(tasks.getTask(taskNumber));
        }
    }

    public boolean isExit() {
        return false;
    }
}
