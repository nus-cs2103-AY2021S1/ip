/**
 * Implements methods for EventCommand.
 */
public class EventCommand extends Command {
    protected boolean isExit;
    protected Task eventTask;

    /**
     * Instantiates EventCommand object.
     * @param description Description of event command.
     * @param eventDate Date of event.
     * @param eventTime Time of event.
     */
    public EventCommand(String description, String eventDate, String eventTime) throws DukeException {
        this.eventTask = new Event(description, eventDate, eventTime);
    }

    /**
     * Runs command to handle event command.
     *
     * @param arrayOfTasks Array of tasks that we have parsed.
     * @param ui Ui object to aid in program execution.
     * @param storage Storage object to aid in program execution.
     * @return Response object
     */
    public Response runCommand(TaskList arrayOfTasks, Ui ui, Storage storage) {
        assert arrayOfTasks != null || ui != null || storage != null
                : "arrayOfTasks, Ui and Storage objects cannot be null";
        arrayOfTasks.addTask(eventTask);
        ui.printTaskCount();
        storage.changeFile();
        return ui.addedMessage(eventTask);
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
