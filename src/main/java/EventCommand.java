/**
 * Represents an event command.
 */
public class EventCommand extends Command {
    private String afterCommand;

    /**
     * Constructor for the event command.
     * @param afterCommand details and time of the event to be completed.
     */
    public EventCommand(String afterCommand) {
        this.afterCommand = afterCommand;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, DukeStorage storage) {
        // needs an after command
        if (afterCommand == null) {
            return ui.throwDukeException(new DukeException(
                    "Please do not leave the event description empty!"));
        }
        // first chunk is the event details, second chunk is at where
        String[] splittedEvent = afterCommand.split(("/"));

        // teach the user the format for the deadline
        if (splittedEvent.length == 1) {
            return ui.throwDukeException(new DukeException("Format of event recording: event keyword"
                + ", event instructions, forward slash, at keyword with a colon, specific date + time)"
                    + "\n e.g. project meeting /at 2020-01-02 14:30"));
        }

        String details = splittedEvent[0].trim();
        String at = splittedEvent[1].split("at", 2)[1].trim();

        try {
            Task newEvent = new Event(details, at);
            taskList.addTask(newEvent);
            return ui.addTask(newEvent, taskList.getTasksSize(), false);
        } catch (DukeException ex) {
            return ui.throwDukeException(ex);
        }
    }

    @Override
    public boolean isCompleted() {
        return false;
    }
}
