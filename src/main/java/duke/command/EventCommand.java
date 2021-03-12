package duke.command;

import java.time.LocalDateTime;

import duke.exception.DukeException;
import duke.logic.Storage;
import duke.task.Event;
import duke.task.TaskManager;
import duke.ui.Ui;

/**
 * Represents a command to add event tasks.
 */
public class EventCommand extends Command {

    /**
     * The Event task to be stored.
     */
    private final Event event;

    /**
     * Constructs a command that adds an event task.
     *
     * @param description The description of the event.
     * @param dateTime The date of the event.
     */
    public EventCommand(String description, LocalDateTime dateTime) {
        event = new Event(description, dateTime);
    }

    @Override
    public String execute(TaskManager manager, Ui ui, Storage storage) throws DukeException {
        manager.addTask(event);
        storage.saveTasks(manager.getTasks());
        return ui.showAddMessage(event, manager.getTasks().size());
    }
}
