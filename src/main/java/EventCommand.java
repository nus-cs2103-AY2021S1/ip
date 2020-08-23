public class EventCommand extends Command {
    protected boolean isExit;
    protected Task eventTask;

    public EventCommand(String description, String eventDate, String eventTime) {
        this.eventTask = new Event(description, eventDate, eventTime);
    }

    public void runCommand(TaskList arrayOfTasks, Ui ui, Storage storage) {
        ui.addedMessage(eventTask);
        arrayOfTasks.addTask(eventTask);
        ui.printTaskCount();
        storage.changeFile();
    }

    public boolean exitCheck() {
        isExit = false;
        return isExit;
    }
}