public class DeleteCommand extends Command{
    int taskIndex;

    public DeleteCommand(int taskIndex){
        super();
        this.taskIndex = taskIndex;
    }

    public void execute(TaskList inputTasks, Storage storage) throws DukeException{
        inputTasks.deleteTask(taskIndex);
        storage.writeToFile(inputTasks);
    }
}
