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
<<<<<<< HEAD
    public void execute(TaskList inputTasks, Storage storage, Ui ui){
        inputTasks.addTask(this.task, ui);
=======
    public void execute(TaskList inputTasks, Storage storage) throws DukeException {
        inputTasks.addTask(this.task);
>>>>>>> master
        storage.writeToFile(inputTasks);
    }
}
