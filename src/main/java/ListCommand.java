/**
 * present commands in a list
 */
public class ListCommand extends Command{
    /**
     * Execute the command
     * @param inputTasks the list of tasks used
     * @param storage the storage used
     */
<<<<<<< HEAD
    public void execute(TaskList inputTasks, Storage storage, Ui ui){
        inputTasks.showTaskList(ui);
        storage.writeToFile(inputTasks);
=======
    public void execute(TaskList inputTasks, Storage storage) throws DukeException {
        inputTasks.showTaskList();
        Storage.writeToFile(inputTasks);
>>>>>>> master
    }
}
