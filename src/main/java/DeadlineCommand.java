public class DeadlineCommand extends Command {

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        Deadline deadline = ui.getDeadline();
        taskList.addTask(deadline);
        ui.addTask(deadline, taskList);
        storage.addData(deadline.store());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
