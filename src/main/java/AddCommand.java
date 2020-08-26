public class AddCommand extends Command {
    
    private final Task task;
    
    AddCommand(Task task) {
        this.task = task;
    }
    
    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.addNewTask(task);
        storage.updateTasks(taskList);
        ui.showAddedTask(task, taskList.getListLength());
    }
}
