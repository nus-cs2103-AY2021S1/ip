package duke.commands;

import java.time.LocalDate;

import duke.tasks.Event;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UI;

/**
 * Represents an Event command when the user wants to add
 * a new Event.
 */
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    private String eventDate;
    private LocalDate eventLocalDate;
    private boolean hasLocalDate;

    /**
     * Creates a new instance of a Event Command with the appropriate
     * event description and date of event given in non standard format.
     *
     * @param commandDescription Description of event.
     * @param eventDate Date of event.
     */
    public EventCommand(String commandDescription, String eventDate) {
        super(commandDescription, false);
        this.eventDate = eventDate;
        this.hasLocalDate = false;
    }

    /**
     * Creates a new instance of a Event Command with the appropriate
     * event description and date of event given in standard format.
     *
     * @param commandDescription Description of the event.
     * @param eventLocalDate Date of event as a LocalDate object.
     */
    public EventCommand(String commandDescription, LocalDate eventLocalDate) {
        super(commandDescription, false);
        this.eventLocalDate = eventLocalDate;
        this.hasLocalDate = true;
    }

    /**
     * Executes the command.
     * Creates a new Event and adds it to the list of tasks.
     *
     * @param taskList List of tasks currently being tracked.
     * @param ui User interface object.
     * @param storage Storage object.
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        Event eventTask;
        if (this.hasLocalDate) {
            eventTask = new Event(commandDescription, eventLocalDate);
        } else {
            eventTask = new Event(commandDescription, eventDate);
        }
        taskList.addToList(eventTask);
        ui.displayAddedTask(eventTask, taskList.getListSize());
    }
}
