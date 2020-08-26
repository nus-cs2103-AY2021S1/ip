package alice.command;

import alice.task.Event;
import alice.task.Task;
import alice.task.TaskList;

import alice.storage.AliceStorageException;
import alice.storage.Storage;

import alice.ui.Ui;

import java.time.LocalDateTime;
import java.util.List;

public class EventCommand extends Command {
    protected static final List<String> NAMES = List.of("event");
    protected static final String DESCRIPTION = "Create an event. Example: event party /on 13-Feb-2020 2359";
    protected static final String USE_CASE = "[" + String.join(", ", NAMES) + "] <desc> /on <datetime>";

    public static boolean hasCommandWord(String name) {
        return NAMES.contains(name);
    }

    private final String description;
    private final LocalDateTime on;

    public EventCommand(String description, LocalDateTime on) {
        this.description = description;
        this.on = on;
    }

    @Override
    public void process(TaskList tasks, Ui ui, Storage storage) throws AliceStorageException {
        Task event = new Event(description, on);
        tasks.addTask(event);
        ui.displayOutput("Roger. I've added the event to your list:\n    " + event
                + "\nNow you have " + tasks.getNumberOfTasks() + " tasks in your list");
        storage.saveToLastLine(event.encode());
    }
}
