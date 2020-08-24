public class AddCommand extends Command {

    Task task;
    protected AddCommand(Task task) {
        this.task = task;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        ui.printAddTask(task, tasks.size());
        storage.writeToFile(tasks.getTasks());
    }
}
