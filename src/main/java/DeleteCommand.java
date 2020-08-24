public class DeleteCommand extends Command {
    private final int taskIndex;
    public DeleteCommand(int taskIndex) {
        super(false);
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskIndex >= tasks.getList().size()) {
            throw new DukeException("Task does not exist _(´ཀ`」 ∠)_");
        } else {
            Task deletedTask = tasks.delete(taskIndex);
            storage.updateFile(tasks);
            ui.printTask(deletedTask, ActionType.MARK_DONE);
        }
    }
}
