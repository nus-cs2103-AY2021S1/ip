public class AddCommand extends Command {
    Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(this.task);
        ui.showAddedMessage(this.task, tasks.getTotalTasks());
    }
}
