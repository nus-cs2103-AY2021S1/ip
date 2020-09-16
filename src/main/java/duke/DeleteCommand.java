package duke;


/**
 * Command for deleting tasks
 */
public class DeleteCommand extends Command {
    private final int taskIndex;
    /**
     * DeleteCommand constructor
     *
     * @param taskIndex Index of class to be deleted
     */
    public DeleteCommand(int taskIndex) {
        super(false);
        this.taskIndex = taskIndex;
    }

    /**
     * Deletes task from list and prints its info
     *
     * @param tasks List of tasks
     * @param ui User interface to print task
     * @param storage File storage object
     * @throws DukeException if exception encountered
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert (taskIndex >= 0);
        if (taskIndex >= tasks.getList().size() || taskIndex < 0) {
            throw new DukeException("Sorry, the task does not exist...");
        } else {
            Task deletedTask = tasks.delete(taskIndex);
            storage.updateFile(tasks);
            return ui.printTask(deletedTask, ActionType.DELETE);
        }
    }
}
