public class AddDeadlineCommand extends Command {
    private final DeadlineTask deadlineTask;

    public AddDeadlineCommand(DeadlineTask deadlineTask) {
        this.deadlineTask = deadlineTask;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui) {
        taskManager.addTask(this.deadlineTask);
        ui.replyAdd(this.deadlineTask, taskManager);
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
