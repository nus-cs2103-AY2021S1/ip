package alice.command;

import alice.task.Task;
import alice.task.TaskList;
import alice.task.Todo;

import alice.storage.AliceStorageException;
import alice.storage.StorageFile;

import alice.ui.Ui;

import java.util.List;

/**
 * Represents the command to add a new todo task in ALICE.
 */
public class TodoCommand extends Command {
    protected static final List<String> NAMES = List.of("todo");
    protected static final String DESCRIPTION = "Create a todo task. Example: todo homework1";
    protected static final String USE_CASE = "[" + String.join(", ", NAMES) + "] <desc>";

    /**
     * Checks if the command word triggers the <code>TodoCommand</code>.
     *
     * @param name the command word to check.
     * @return true if the command word belongs to <code>TodoCommand</code>; false otherwise.
     */
    public static boolean hasCommandWord(String name) {
        return NAMES.contains(name);
    }

    private final String description;

    /**
     * Creates a new command to create a new <code>Todo</code> with the provided description.
     *
     * @param description the description of the task to be done.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * {@inheritDoc}
     *
     * @throws AliceStorageException if there were errors writing to storageFile.
     */
    @Override
    public void process(TaskList tasks, Ui ui, StorageFile storageFile) throws AliceStorageException {
        Task todo = new Todo(description);
        tasks.addTask(todo);
        ui.displayOutput("Roger. I've added the task to your list:\n    " + todo
                + "\nNow you have " + tasks.getNumberOfTasks() + " tasks in your list");
        storageFile.saveToLastLine(todo.encode());
    }
}
