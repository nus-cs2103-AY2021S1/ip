package alice.command;

import java.time.LocalDateTime;
import java.util.List;

import alice.command.result.CommandResult;
import alice.command.result.EventCommandResult;
import alice.storage.AliceStorageException;
import alice.storage.SaveStatus;
import alice.storage.StorageFile;
import alice.task.Event;
import alice.task.Task;
import alice.task.TaskList;

/**
 * Represents the command to add a new event in ALICE.
 */
public class EventCommand implements Command {
    protected static final List<String> NAMES = List.of("event");
    protected static final String DESCRIPTION = "Create an event";
    protected static final String USE_CASE = "[" + String.join(", ", NAMES)
            + "] <desc> /on <datetime>";

    private final String description;
    private final LocalDateTime on;

    /**
     * Creates a new command to create a new <code>Event</code> with the details provided.
     *
     * @param description the description of the event.
     * @param on          the datetime of when the event is happening.
     */
    public EventCommand(String description, LocalDateTime on) {
        this.description = description;
        this.on = on;
    }

    /**
     * Checks if the command word triggers the <Code>EventCommand</Code>.
     *
     * @param name the command word to check.
     * @return true if the command word belongs to <Code>EventCommand</Code>; false otherwise.
     */
    public static boolean hasCommandWord(String name) {
        return NAMES.contains(name);
    }

    @Override
    public CommandResult process(TaskList tasks, StorageFile storageFile) {
        Task event = new Event(description, on);
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
