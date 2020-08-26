package alice.command;

import alice.task.Task;
import alice.task.TaskList;
import alice.task.Todo;

import alice.storage.AliceStorageException;
import alice.storage.Storage;

import alice.ui.Ui;

import java.util.List;

public class TodoCommand extends Command {
    protected static final List<String> NAMES = List.of("todo");
    protected static final String DESCRIPTION = "Create a todo task. Example: todo homework1";
    protected static final String USE_CASE = "[" + String.join(", ", NAMES) + "] <desc>";

    public static boolean hasCommandWord(String name) {
        return NAMES.contains(name);
    }

    private final String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void process(TaskList tasks, Ui ui, Storage storage) throws AliceStorageException {
        Task todo = new Todo(description);
        tasks.addTask(todo);
        ui.displayOutput("Roger. I've added the task to your list:\n    " + todo
                + "\nNow you have " + tasks.getNumberOfTasks() + " tasks in your list");
        storage.saveToLastLine(todo.encode());
    }
}
