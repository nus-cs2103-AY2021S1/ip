package alice.command;

import alice.task.Deadline;
import alice.task.Task;
import alice.task.TaskList;

import alice.storage.AliceStorageException;
import alice.storage.Storage;

import alice.ui.Ui;

import java.time.LocalDateTime;
import java.util.List;

public class DeadlineCommand extends Command {
    protected static final List<String> NAMES = List.of("deadline");
    protected static final String DESCRIPTION = "Create a task with a deadline. Example: deadline assignment /by 10-Aug 2359";
    protected static final String USE_CASE = "[" + String.join(", ", NAMES) + "] <desc> /by <datetime>";

    public static boolean hasCommandWord(String name) {
        return NAMES.contains(name);
    }

    private final String description;
    private final LocalDateTime by;

    public DeadlineCommand(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void process(TaskList tasks, Ui ui, Storage storage) throws AliceStorageException {
        Task deadline = new Deadline(description, by);
        tasks.addTask(deadline);
        ui.displayOutput("Roger. I've added the deadline to your list:\n    " + deadline
                + "\nNow you have " + tasks.getNumberOfTasks() + " tasks in your list");
        storage.saveToLastLine(deadline.encode());
    }
}
