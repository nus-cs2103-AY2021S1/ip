public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui) {
        taskManager.addTask(task);
        ui.replyAdd(task, taskManager);
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
