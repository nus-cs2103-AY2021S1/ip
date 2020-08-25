package commands;

import data.task.Event;

import java.time.LocalDateTime;

// Adds a data.tasks.Event to the data.task list.
public class AddEventCommand extends AddCommand {

    public static final String COMMAND_WORD = "event";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an event to the task list.\n"
            + "\tParameters: DESCRIPTION /at DATE TIME\n"
            + "\tExample: " + COMMAND_WORD + " project meeting /at 05/09/2020 1430";

    private final Event toAdd;

    public AddEventCommand(String description, LocalDateTime dateTime) {
        this.toAdd = new Event(description, dateTime);
    }

    public AddEventCommand(Event toAdd) {
        this.toAdd = toAdd;
    }

    public Event getEvent() {
        return toAdd;
    }

    @Override
    public CommandResult execute() {
        taskList.add(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd, taskList.size()));
    }
}
