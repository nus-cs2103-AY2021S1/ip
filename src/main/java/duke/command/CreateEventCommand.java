package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import duke.task.Event;

import java.time.LocalDate;

/**
 * Encapsulates a command to create an event
 */
public class CreateEventCommand extends Command {

    /** Date of the event */
    private final LocalDate date;

    /** Description of the event */
    private final String description;

    /** Initial completion status of the event */
    private final boolean isComplete;

    /**
     * Constructor
     *
     * @param description Description of the event
     * @param isComplete Initial completion status of the event
     * @param date Date of the event
     */
    public CreateEventCommand(String description, boolean isComplete, LocalDate date) {
        this.date = date;
        this.description = description;
        this.isComplete = isComplete;
    }

    /**
     * Executes the command to create an event.
     *
     * @param storage Storage
     * @param taskList Task list
     * @param archive Archive
     * @param ui Ui
     * @return Output strings displayed on the UI showing event creation
     */
    @Override
    public String[] execute(Storage storage, TaskList taskList, TaskList archive, Ui ui) {
        assert storage != null;
        assert taskList != null;
        assert ui != null;

        Event newEvent = taskList.addEvent(description, isComplete, date);
        return ui.getCreateTaskStrings(taskList, newEvent);
    }
}
