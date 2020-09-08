public class DoneCommand extends Command{
    int taskIndex;

    public DoneCommand(int taskIndex){
        super();
        this.taskIndex = taskIndex;
    }

    public void execute(TaskList inputTasks, Storage storage) throws DukeException{
        inputTasks.doneTask(taskIndex);
        storage.writeToFile(inputTasks);
    }
}
