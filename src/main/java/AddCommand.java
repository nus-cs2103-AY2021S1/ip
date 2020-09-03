public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException{
        taskList.addTask(task);
        storage.storeTaskList(taskList);
        ui.showTaskAdded(task);
    }

    @Override
    public Boolean isExit() {
        return false;
    }

    public Task getTask() {
        return task;
    }
}
