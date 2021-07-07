package duke.commands;
import java.time.LocalDate;

import duke.data.task.Event;
import duke.data.task.TaskList;
import duke.storage.Storage;

/**
 * Responsible for the logic of adding an event.
 */
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Creates an event with a description and date of event.\n"
            + "Example: " + COMMAND_WORD + " CS2103T Lecture /at 2020-10-10\n";
    private Event event;

    /**
     * Constructor initialising description and date of event.
     * @param description Name of the Event.
     * @param date Date of the Event.
     */
    public EventCommand(String description, LocalDate date) {
        this.description = description;
        event = new Event(description, date);
    }

    /**
     * Adds an event to the task list and appends it to the data file.
     * @param tasks List of tasks.
     * @param storage Saves tasks in text file.
     * @return CommandResult noting that the event has been added.
     */
    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        tasks.addTask(event);
        storage.save(event);
        return new CommandResult("Added: " + event);
    }
}
