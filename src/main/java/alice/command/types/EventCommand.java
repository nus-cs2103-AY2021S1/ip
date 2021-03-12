package alice.command.types;

import java.util.List;

import alice.command.InvalidCommandException;
import alice.command.result.CommandResult;
import alice.command.result.EventCommandResult;
import alice.storage.AliceStorageException;
import alice.storage.SaveStatus;
import alice.storage.StorageFile;
import alice.task.Event;
import alice.task.Task;
import alice.task.TaskList;
import alice.task.time.TaskDateTime;

/**
 * Represents the command to add a new event in ALICE.
 */
public class EventCommand implements Command {
    protected static final List<String> NAMES = List.of("event");
    protected static final String DESCRIPTION = "Create an event";
    protected static final String USE_CASE = "[" + String.join(", ", NAMES)
            + "] <desc> /at <date> <time>";

    private final String description;
    private final TaskDateTime at;

    /**
     * Creates a new command to create a new {@code Event} with the details provided.
     *
     * @param description the description of the event.
     * @param at          the datetime of when the event is happening.
     */
    private EventCommand(String description, TaskDateTime at) {
        this.description = description;
        this.at = at;

        assert !description.isBlank() : "Cannot create an EventCommand without providing description";
    }

    /**
     * Checks if the command word triggers the {@code EventCommand}.
     *
     * @param name the command word to check.
     * @return true if the command word belongs to {@code EventCommand}; false otherwise.
     */
    public static boolean hasCommandWord(String name) {
        return NAMES.contains(name);
    }

    /**
     * Creates a new command to create a new {@code Event} with the details given by the user.
     *
     * @param argument the event details input given by user.
     * @return the {@code EventCommand} with the indicated details.
     * @throws InvalidCommandException if the user gives an invalid description and/or datetime.
     */
    public static EventCommand createCommand(String argument) throws InvalidCommandException {
        if (argument.isBlank() || argument.startsWith("/at")) {
            // Empty event description
            throw new InvalidCommandException("The event description cannot be left empty.");
        }

        if (argument.endsWith("/at")) {
            // Empty start-end time
            throw new InvalidCommandException("You cannot create an event without a date/time.");
        }

        String[] arguments = argument.split(" /at ", 2);
        if (arguments.length == 2 && !arguments[1].isBlank()) {
            String description = arguments[0];
            String dateTime = arguments[1];

            if (description.contains(" | ")) {
                throw new InvalidCommandException("Invalid character detected in event description.");
            }

            TaskDateTime eventDateTime = TaskDateTime.parseDateTime(dateTime);
            return new EventCommand(description, eventDateTime);
        } else {
            // No /at marker
            throw new InvalidCommandException("I can't find the date/time of the event.\n"
                    + "Did you forget to add '/at'?");
        }
    }

    @Override
    public CommandResult process(TaskList tasks, StorageFile storageFile) {
        Task event = new Event(description, at);
        tasks.addTask(event);
        String reply = "Roger. I've added the event to your list:\n    " + event
                + "\nNow you have " + tasks.getNumberOfTasks() + " tasks in your list";
        try {
            storageFile.saveToLastLine(event.encode());
            return new EventCommandResult(reply, true, SaveStatus.SAVE_SUCCESS);
        } catch (AliceStorageException ex) {
            return new EventCommandResult(reply, true, SaveStatus.SAVE_FAILED);
        }
    }
}
