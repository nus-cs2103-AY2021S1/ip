package alice.command;

import alice.task.Event;
import alice.task.Task;
import alice.task.TaskList;

import alice.storage.AliceStorageException;
import alice.storage.StorageFile;

import alice.ui.Ui;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents the command to add a new event in ALICE.
 */
public class EventCommand extends Command {
    protected static final List<String> NAMES = List.of("event");
    protected static final String DESCRIPTION = "Create an event. "
            + "Example: event party /on 13-Feb-2020 2359";
    protected static final String USE_CASE = "[" + String.join(", ", NAMES)
            + "] <desc> /on <datetime>";

    /**
     * Checks if the command word triggers the <Code>EventCommand</Code>.
     *
     * @param name the command word to check.
     * @return true if the command word belongs to <Code>EventCommand</Code>; false otherwise.
     */
    public static boolean hasCommandWord(String name) {
        return NAMES.contains(name);
    }

    private final String description;
    private final LocalDateTime on;

    /**
     * Creates a new command to create a new <code>Event</code> with the details provided.
     *
     * @param description the description of the event.
     * @param on the datetime of when the event is happening.
     */
    public EventCommand(String description, LocalDateTime on) {
        this.description = description;
        this.on = on;
    }

    /**
     * {@inheritDoc}
     *
     * @throws AliceStorageException if there were errors writing to storageFile.
     */
    @Override
    public void process(TaskList tasks, Ui ui, StorageFile storageFile) throws AliceStorageException {
        Task event = new Event(description, on);
        tasks.addTask(event);
        ui.displayOutput("Roger. I've added the event to your list:\n    " + event
                + "\nNow you have " + tasks.getNumberOfTasks() + " tasks in your list");
        storageFile.saveToLastLine(event.encode());
    }
}
