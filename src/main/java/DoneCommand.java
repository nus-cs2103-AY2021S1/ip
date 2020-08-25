public class DoneCommand extends Command {
    private final int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    protected void execute(TaskList taskList, Ui ui, Storage storage)  {
        taskList.setDoneTask(index, storage);
    }

    @Override
    protected boolean isExit() {
        return false;
    }
}
