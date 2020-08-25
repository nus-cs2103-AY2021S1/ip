public class DeleteCommand extends Command {
    private int taskNo;

    public DeleteCommand(int taskNo) {
        super("delete");
        this.taskNo = taskNo;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        tasks.deleteTask(taskNo);
        // ui.showDeleteMessage();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
