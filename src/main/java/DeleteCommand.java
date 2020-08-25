public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        Task removedTask = tasks.removeTask(taskIndex);
        ui.displayDeleteTask(removedTask, tasks.numTasks());
    }
}
