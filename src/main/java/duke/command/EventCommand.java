package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.Event;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * Represents a call to create a new Event Task.
 */
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";

    private final String description;
    private final String eventDate;

    /**
     * Constructor for EventCommand.
     * @param description Description of the Event Task.
     * @param eventDate Time at which Event occurs in String form.
     */
    public EventCommand(String description, String eventDate) {
        this.description = description;
        this.eventDate = eventDate;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = new Event(description, eventDate);
        tasks.add(t);
        ui.addTaskMessage(t, tasks);
    }
}
