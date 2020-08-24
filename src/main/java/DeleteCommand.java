public class DeleteCommand extends Command {

    private int taskNo;

    DeleteCommand(int taskNo) {
        this.taskNo = taskNo;
        isExit = false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.deleteTask(taskNo);
        storage.saveTasks(taskList.getList());
    }
}