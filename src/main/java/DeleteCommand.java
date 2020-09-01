public class DeleteCommand extends Command {
    private final int taskIndex;

    public DeleteCommand(int taskNum) {
        this.taskIndex = taskNum;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui) {
        ui.replyDelete(taskManager.getTask(taskIndex - 1));
        taskManager.removeTask(taskIndex - 1);
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
