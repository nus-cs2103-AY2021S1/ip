public class AddCommand extends Command {

    public Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        ui.formatAddTask(tasks.lst, task);
        storage.saveTaskList(tasks.lst);
    }
}
