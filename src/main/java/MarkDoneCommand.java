public class MarkDoneCommand extends Command {
    private final int taskIndex;
    public MarkDoneCommand(int taskIndex) {
        super(false);
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskIndex >= tasks.size()) {
            throw new DukeException("Task does not exist _(´ཀ`」 ∠)_");
        } else {
        Task completedTask = tasks.markDone(taskIndex);
        storage.updateFile(tasks);
        ui.printTask(completedTask, ActionType.MARK_DONE);
        }
    }
}
