package duke.command;

import duke.Storage;
import duke.TaskManager;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents a command to add todos.
 */
public class TodoCommand extends AddCommand {

    /**
     * Constructs a command that adds a todo.
     *
     * @param description The description of the task.
     */
    public TodoCommand(String description) {
        super(description);
    }

    @Override
    public void execute(TaskManager manager, Ui ui, Storage storage) throws DukeException {
        Task task = new Todo(description);
        manager.addTask(task);
        ui.showAddMessage(task, manager.getTasks().size());
        storage.saveTasks(manager.getTasks());
    }
}