/**
 * Represents a command to mark a task as done.
 */
public class DoneCommand extends Command {
    protected int taskNumber;

    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Marks a task as done and updates it in both the task list and file in filepath specified in storage.
     * @param tasks Task list of all tasks.
     * @param ui Ui to deal with interaction with user.
     * @param storage Storage to load and save tasks.
     * @throws DukeException
     */
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
