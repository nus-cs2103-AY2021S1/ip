/**
 * Implements methods for DoneCommand.
 */
public class DoneCommand extends Command {
    protected int taskIndex;
    protected boolean isExit;

    /**
     * Instantiates DoneCommand object.
     * @param taskIndex Index of task to be deleted.
     */
    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Runs command to handle done command.
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
            throw new DukeException("The task number that you have input " + "(" + (taskIndex + 1) + ") "
                                    + "can not be found in your list.");
        }
        arrayOfTasks.get(taskIndex).setDone();
        Task doneTask = arrayOfTasks.get(taskIndex);
        storage.changeFile();
        return ui.doneMessage(doneTask);
    }

    /**
     * Since this is not a exit command, it does not signal the program to exit.
     *
     * @return exitCheck as False
     */
    public boolean exitChecker() {
        isExit = false;
        return isExit;
    }
}
