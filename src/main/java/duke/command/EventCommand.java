package duke.command;

import java.time.LocalDate;

import duke.Storage;
import duke.TaskManager;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Event;
import duke.task.Task;

/**
 * Represents a command to add event tasks.
 */
public class EventCommand extends AddCommand {

    /**
     * A local date instance to store the date of the event.
     */
    private final LocalDate date;

    /**
     * Constructs a command that adds an event task.
     * @param description the description of the event
     * @param date the date of the event
     */
    public EventCommand(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    @Override
    public void execute(TaskManager manager, Ui ui, Storage storage) throws DukeException {
        Task task = new Event(description, date);
        manager.addTask(task);
        ui.showAddMessage(task, manager.getTasks().size());
        storage.saveTasks(manager.getTasks());
    }
}