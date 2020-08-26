public class DeleteCommand extends Command {
    
    private final int taskIndex;

    DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = taskList.deleteTask(taskIndex);
        storage.updateTasks(taskList);
        ui.showDeleteTask(task, taskList.getListLength());
    }
}
