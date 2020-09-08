public class ListCommand extends Command{
    public void execute(TaskList inputTasks, Storage storage){
        inputTasks.showTaskList();
        storage.writeToFile(inputTasks);
    }
}
