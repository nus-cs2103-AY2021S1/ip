public class DeleteCommand extends Command {

    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.printDeleteMessage(taskList.deleteTask(taskNumber), taskList.getCount());
        storage.updateTasks(taskList);
    }

    @Override
    public boolean isInProgram() {
        return true;
    }
}
