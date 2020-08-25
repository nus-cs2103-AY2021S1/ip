public class DoneCommand extends Command {
    private int taskNo;

    public DoneCommand(int taskNo) {
        super("done");
        this.taskNo = taskNo;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.doneTasks(taskNo);
        // ui.showDoneMessage();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
