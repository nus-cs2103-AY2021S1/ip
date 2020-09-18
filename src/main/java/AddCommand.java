/**
 * Add a task according to commands given
 */
public class AddCommand extends Command{
    Task task;

    /**
     * AddCommand constructor
     * @param task task to be added
     */
    public AddCommand(Task task){
        super();
        this.task = task;
    }

    /**
     * Execute the command
     * @param inputTasks the list of tasks used
     * @param storage the storage used
     */
    public void execute(TaskList inputTasks, Storage storage, Ui ui){
        inputTasks.addTask(this.task, ui);
        storage.writeToFile(inputTasks);
    }
}
