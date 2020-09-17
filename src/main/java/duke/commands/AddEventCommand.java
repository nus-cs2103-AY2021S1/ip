package duke.commands;

import java.time.LocalDateTime;

import duke.data.task.Event;

/**
 * Adds an Event to the task list.
 */
public class AddEventCommand extends AddCommand {

    public static final String COMMAND_WORD = "event";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " :\n Adds an event to the task list.\n"
            + "  Parameters: DESC /at DATE TIME\n"
            + "  Example: " + COMMAND_WORD + " project meeting /at 05/09/2020 1430";

    private final Event toAdd;

    public AddEventCommand(String description, LocalDateTime dateTime) {
        this.toAdd = new Event(description, dateTime);
    }

    @Override
    public CommandResult execute() {
        taskList.add(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd, taskList.size()));
    }

}
