/**
 * Represents an add command which allows users to add tasks to the TaskList.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Creates an AddCommand object.
     *
     * @param task the task to be added
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the addition of tasks. The user will be notified through the 
     * printed messages by the ui and the current tasks are saved.
     *
     * @param tasks the current TaskList object being used
     * @param ui the current Ui object being used 
     * @param storage the current Storage object being used
     * @throws PandaBotException If any errors occurs when executing the command
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PandaBotException {
        // add the task
        tasks.addTask(task);
        
        // print
        ui.printOnAddTask(task, tasks.size());
        
        // save
        storage.write(tasks.getTaskList());
    }
}
