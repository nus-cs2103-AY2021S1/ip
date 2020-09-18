/**
 * Implements methods for DeleteCommand.
 */
public class DeleteCommand extends Command {
    protected boolean isExit;
    protected int taskIndex;

    /**
     * Instantiates DeleteCommand object.
     * @param taskIndex Index of task to be deleted.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Runs command to handle delete command.
     *
     * @param arrayOfTasks Array of tasks that we have parsed.
     * @param ui Ui object to aid in program execution.
     * @param storage Storage object to aid in program execution.
     * @throws DukeException if there is an issue.
     */
    public Response runCommand(TaskList arrayOfTasks, Ui ui, Storage storage) throws DukeException {
        assert arrayOfTasks != null || ui != null || storage != null
                : "arrayOfTasks, Ui and Storage objects cannot be null";
        int arraySize = arrayOfTasks.taskArraySize();
        if (taskIndex < 0 || taskIndex >= arraySize) {
            throw new DukeException("The task number that you want to delete " + "(" + (taskIndex + 1) + ") "
                                    + "can not be found in your list.");
        }
        Task taskToDelete = arrayOfTasks.get(taskIndex);
        arrayOfTasks.deleteTask(taskIndex);
        ui.printTaskCount();
        storage.changeFile();
        return ui.removeMessage(taskToDelete);
    }

    /**
     * Checks if the program has to exit Duke.
     *
     * @return exitCheck as False
     */
    public boolean exitChecker() {
        isExit = false;
        return isExit;
    }
}
