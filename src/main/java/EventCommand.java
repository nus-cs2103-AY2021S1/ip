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
    public void execute(TaskList taskList, Ui ui, DukeStorage storage) {
        // needs an after command
        if (afterCommand == null) {
            ui.throwDukeException(new DukeException("Please do not leave the event description empty!"));
            return;
        }
        // first chunk is the event details, second chunk is at where
        String[] splittedEvent = afterCommand.split(("/"));

        // teach the user the format for the deadline
        if (splittedEvent.length == 1) {
            ui.throwDukeException(new DukeException("Format of event recording: event keyword" +
                    ", event instructions, forward slash, at keyword with a colon, start/end time)"
                    + "\n e.g. project meeting /at Mon 2-4pm"));
        } else {
            String details = splittedEvent[0].trim();
            String at = splittedEvent[1].split("at", 2)[1].trim();

            try {
                Task newEvent = new Event(details, at);
                taskList.addTask(newEvent);
                ui.addTask(newEvent, taskList.tasksSize());
            } catch (DukeException ex) {
                ui.throwDukeException(ex);
            }
        }
    }

    @Override
    public boolean isCompleted() {
        return false;
    }
}
