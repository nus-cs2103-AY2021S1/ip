public class DeleteCommand extends Command {
    private final int taskIndex;

    public DeleteCommand(int taskNum) {
        this.taskIndex = taskNum;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui) throws DukeException {
        assert (taskIndex >= 1) : "Task index must be more than 0";
        ui.replyDelete(taskManager.getTask(taskIndex - 1));
        taskManager.removeTask(taskIndex - 1);
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
