package alice.command.types;

import java.util.List;

import alice.command.InvalidCommandException;
import alice.command.result.CommandResult;
import alice.command.result.TodoCommandResult;
import alice.storage.AliceStorageException;
import alice.storage.SaveStatus;
import alice.storage.StorageFile;
import alice.task.Task;
import alice.task.TaskList;
import alice.task.Todo;

/**
 * Represents the command to add a new todo task in ALICE.
 */
public class TodoCommand implements Command {
    protected static final List<String> NAMES = List.of("todo");
    protected static final String DESCRIPTION = "Create a todo task";
    protected static final String USE_CASE = "[" + String.join(", ", NAMES) + "] <desc>";

    private final String description;

    /**
     * Creates a new command to create a new {@code Todo} with the provided description.
     *
     * @param description the description of the task to be done.
     */
    private TodoCommand(String description) {
        this.description = description;

        assert !description.isBlank() : "Cannot create a TodoCommand without providing description";
    }

    /**
     * Checks if the command word triggers the {@code TodoCommand}.
     *
     * @param name the command word to check.
     * @return true if the command word belongs to {@code TodoCommand}; false otherwise.
     */
    public static boolean hasCommandWord(String name) {
        return NAMES.contains(name);
    }

    /**
     * Creates a new command to create a new {@code Todo} with the description given by the user.
     *
     * @param description the description input given by user.
     * @return the {@code TodoCommand} with the verified description.
     * @throws InvalidCommandException if the user gives an empty description.
     */
    public static TodoCommand createCommand(String description) throws InvalidCommandException {
        if (description.isBlank()) {
            throw new InvalidCommandException("The todo description cannot be left empty.");
        }

        if (description.contains(" | ")) {
            throw new InvalidCommandException("Invalid character detected in todo description.");
        }

        return new TodoCommand(description);
    }

    @Override
    public CommandResult process(TaskList tasks, StorageFile storageFile) {
        Task todo = new Todo(description);
        tasks.addTask(todo);
        String reply = "Roger. I've added the task to your list:\n    " + todo
                + "\nNow you have " + tasks.getNumberOfTasks() + " tasks in your list!";
        try {
            storageFile.saveToLastLine(todo.encode());
            return new TodoCommandResult(reply, true, SaveStatus.SAVE_SUCCESS);
        } catch (AliceStorageException ex) {
            return new TodoCommandResult(reply, true, SaveStatus.SAVE_FAILED);
        }
    }
}
