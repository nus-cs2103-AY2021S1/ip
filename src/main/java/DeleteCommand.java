public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.deleteTask(taskIndex);
        storage.storeTaskList(taskList);
        ui.showTaskDeleted(taskIndex);
    }

    @Override
    public Boolean isExit() {
        return false;
    }
}
