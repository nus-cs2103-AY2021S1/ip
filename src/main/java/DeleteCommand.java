public class DeleteCommand extends Command {

    public DeleteCommand(String args) {
        super(args);
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws InvalidTaskIndexException, StorageException {
        Task deletedTask = taskList.deleteTask(args);
        storage.save(taskList);
        return deletedTask.toString();
    }
}
