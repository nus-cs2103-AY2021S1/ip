public class AddCommand extends Command{
    Task task;

    public AddCommand(Task task){
        super();
        this.task = task;
    }

    public void execute(TaskList inputTasks, Storage storage) throws DukeException{
        inputTasks.addTask(this.task);
        storage.writeToFile(inputTasks);
    }
}
