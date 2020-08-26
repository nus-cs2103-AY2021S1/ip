public class DoneCommand extends Command {

    int taskNumber;

    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markTaskAsDone(taskNumber);
        Task currTask = tasks.getTask(taskNumber);
        ui.showDoneMessage(currTask);
    }
}
