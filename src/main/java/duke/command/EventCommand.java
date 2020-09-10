package duke.command;

import duke.action.Action;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Event;

import duke.ui.Ui;

import duke.storage.Storage;

import java.util.Queue;

/**
 * Represents a call to create a new Event Task.
 */
public class EventCommand extends Command {

    private final String description;
    private final String eventDate;

    public static final String COMMAND_WORD = "event";

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
    public void execute(Ui ui, Storage storage, TaskList tasks, Queue<Action> commandQueue) throws DukeException {
        Task t = Event.createEvent(description, eventDate);
        tasks.add(t);
        ui.addTaskMessage(t, tasks);
    }
}
