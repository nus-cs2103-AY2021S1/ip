/**
 * Implements methods for DeadlineCommand.
 */
public class DeadlineCommand extends Command {
    protected boolean isExit;
    protected Task deadlineTask;

    /**
     * Instantiates DeadlineCommand object.
     * @param description Description of deadline command.
     * @param deadlineDate Date of deadline.
     * @param deadlineTime Time of deadline.
     */
    public DeadlineCommand(String description, String deadlineDate, String deadlineTime) throws DukeException {
        this.deadlineTask = new Deadline(description, deadlineDate, deadlineTime);
    }

    /**
     * Runs command to handle deadline command.
     *
     * @param arrayOfTasks Array of tasks that we have parsed.
     * @param ui Ui object to aid in program execution.
     * @param storage Storage object to aid in program execution.
     * @return Response object
     */
    public Response runCommand(TaskList arrayOfTasks, Ui ui, Storage storage) {
        assert arrayOfTasks != null || ui != null || storage != null
                : "arrayOfTasks, Ui and Storage objects cannot be null";
        arrayOfTasks.addTask(deadlineTask);
        ui.printTaskCount();
        storage.changeFile();
        return ui.addedMessage(deadlineTask);
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
