public class DoneCommand extends Command {
    private int taskNo;

    DoneCommand(int taskNo) {
        this.taskNo = taskNo;
        isExit = false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.markDone(taskNo);
        storage.saveTasks(taskList.getList());
    }
}
