/**
 * present commands in a list
 */
public class ListCommand extends Command{
    /**
     * Execute the command
     * @param inputTasks the list of tasks used
     * @param storage the storage used
     */
    public void execute(TaskList inputTasks, Storage storage) throws DukeException {
        inputTasks.showTaskList();
        Storage.writeToFile(inputTasks);
    }
}
