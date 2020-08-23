public class DeadlineCommand extends Command {
    boolean exitCheck;
    Task deadlineTask;

    /**
     * Instantiates DeadlineCommand object.
     * @param description Description of deadline command.
     * @param deadlineDate Date of deadline.
     * @param deadlineTime Time of deadline.
     */
    public DeadlineCommand(String description, String deadlineDate, String deadlineTime) {
        this.deadlineTask = new Deadline(description, deadlineDate, deadlineTime);
    }

    /**
     * Runs command to handle deadline command.
     *
     * @param arrayOfTasks Array of tasks that we have parsed.
     * @param ui Ui object to aid in program execution.
     * @param storage Storage object to aid in program execution.
     * @return void
     */
    public void runCommand(TaskList arrayOfTasks, Ui ui, Storage storage) {
        ui.addedMessage(deadlineTask);
        arrayOfTasks.addTask(deadlineTask);
        ui.printTaskCount();
        storage.changeFile();
    }

    /**
     * Checks if the program has to exit Duke.
     *
     * @return exitCheck as False
     */
    public boolean exitCheck() {
        exitCheck = false;
        return exitCheck;
    }
}