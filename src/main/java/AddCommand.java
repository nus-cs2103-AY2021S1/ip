public class AddCommand extends Command {
    public Task task;
    
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        return ui.showAddedTask(task, tasks.taskListLength());
        
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
