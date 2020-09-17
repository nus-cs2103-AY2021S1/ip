/**
 * Mark task as done according to command
 */
public class DoneCommand extends Command{
    int taskIndex;

    /**
     * DoneCommand constructor
     * @param taskIndex the index of the task to be done
     */
    public DoneCommand(int taskIndex){
        super();
        this.taskIndex = taskIndex;
    }

    /**
     * Execute the command
     * @param inputTasks the list of tasks used
     * @param storage the storage used
     * @throws DukeException throws exceptions that fail to fulfil command requirements
     */
    public void execute(TaskList inputTasks, Storage storage, Ui ui) throws DukeException{
        inputTasks.doneTask(taskIndex, ui);
        storage.writeToFile(inputTasks);
    }
}
