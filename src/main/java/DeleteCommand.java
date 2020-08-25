public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    protected void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.deleteTask(index, storage);
    }

    @Override
    protected boolean isExit() {
        return false;
    }
}
