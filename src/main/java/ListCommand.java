/**
 * Implements methods for ListCommand.
 */
public class ListCommand extends Command {
    protected boolean isExit;

    /**
     * Runs command to handle list command.
     *
     * @param arrayOfTasks Array of tasks that we have parsed.
     * @param ui Ui object to aid in program execution.
     * @param storage Storage object to aid in program execution.
     * @return Response object
     */
    public Response runCommand(TaskList arrayOfTasks, Ui ui, Storage storage) {
        assert arrayOfTasks != null || ui != null || storage != null
                : "arrayOfTasks, Ui and Storage objects cannot be null";
        try {
            if (arrayOfTasks.taskArraySize() == 0) {
                throw new DukeException("There is nothing to list as the list is currently empty.");
            }
            return ui.listTasks(arrayOfTasks);
        } catch (DukeException error) {
            return ui.listError();
        }
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
