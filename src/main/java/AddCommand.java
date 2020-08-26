public class AddCommand extends Command {
    public Task task;
    
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showAddedTask(task, tasks.taskListLength());
        tasks.addTask(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
