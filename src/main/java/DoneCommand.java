public class DoneCommand extends Command {
    private final int taskIndex;

    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui) {
        ui.replyDone(taskManager.getTask(taskIndex - 1));
        taskManager.markTaskAsDone(taskIndex - 1);
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
