abstract class TaskCreationCommand extends Command {
    public void execute(Task task, Ui ui, TaskList tasks) {
        tasks.add(task);
        ui.showAddSuccessful(task, tasks);
    }
}
