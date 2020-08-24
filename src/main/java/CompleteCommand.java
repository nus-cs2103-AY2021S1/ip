public class CompleteCommand extends Command {

    protected CompleteCommand(String args){
        super(args);
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws TaskException, StorageException {
        Task completedTask = taskList.completeTask(args);
        storage.save(taskList);
        return "Neat! Marking this as complete:\n" + args + ". " + completedTask.toString();
    }
}
