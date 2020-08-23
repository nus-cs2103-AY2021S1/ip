public class TodoCommand extends Command {
    protected Task toDoTask;
    protected boolean isExit;

    /**
     * Instantiates TodoCommand object.
     * @param description Description of todo command.
     */
    public TodoCommand(String description) {
        toDoTask = new Todo(description);
    }

    /**
     * Runs command to handle todo command.
     *
     * @param arrayOfTasks Array of tasks that we have parsed.
     * @param ui Ui object to aid in program execution.
     * @param storage Storage object to aid in program execution.
     * @return void
     */
    public void runCommand(TaskList arrayOfTasks, Ui ui, Storage storage) {
        ui.addedMessage(toDoTask);
        arrayOfTasks.addTask(toDoTask);
        ui.printTaskCount();
        storage.changeFile();
    }

    /**
     * Checks if the program has to exit Duke.
     *
     * @return exitCheck as False
     */
    public boolean exitCheck() {
        isExit = false;
        return isExit;
    }
}