public class DeleteCommand extends Command {
    private final int taskNum;

    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui) {
        taskManager.removeTask(taskNum);
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
