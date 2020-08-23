public class EventCommand extends Command {
    boolean exitCheck;
    Task eventTask;

    /**
     * Instantiates EventCommand object.
     * @param description Description of event command.
     * @param eventDate Date of event.
     * @param eventTime Time of event.
     */
    public EventCommand(String description, String eventDate, String eventTime) {
        this.eventTask = new Event(description, eventDate, eventTime);
    }

    /**
     * Runs command to handle event command.
     *
     * @param arrayOfTasks Array of tasks that we have parsed.
     * @param ui Ui object to aid in program execution.
     * @param storage Storage object to aid in program execution.
     * @return void
     */
    public void runCommand(TaskList arrayOfTasks, Ui ui, Storage storage) {
        ui.addedMessage(eventTask);
        arrayOfTasks.addTask(eventTask);
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