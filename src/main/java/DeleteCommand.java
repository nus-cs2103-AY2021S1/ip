public class DeleteCommand extends Command {
    int idx;

    DeleteCommand(int idx) {
        super();
        this.idx = idx;
    }

    public void execute(TaskList taskList, Storage storage) throws DukeException {
        taskList.deleteTask(this.idx);
        storage.writeToFile(taskList);
    }
}
