package commands;
import java.time.LocalDate;

import duke.data.task.Event;
import duke.data.task.TaskList;
import duke.storage.Storage;


public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Creates an event with a description and date of event.\n"
            + "Example: " + COMMAND_WORD + " CS2103T Lecture /at 2020-10-10";
    private Event event;

    public EventCommand(String description, LocalDate date) {
        this.description = description;
        event = new Event(description, date);
    }

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        tasks.addTask(event);
        storage.save(event);
        return new CommandResult("Added: " + event);
    }
}
