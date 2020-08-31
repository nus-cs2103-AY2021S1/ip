package duke.command;

import duke.exception.DukeException;
import duke.logic.Storage;
import duke.task.Task;
import duke.task.TaskManager;
import duke.task.Todo;
import duke.ui.Ui;

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
    public String execute(TaskManager manager, Ui ui, Storage storage) throws DukeException {
        Task task = new Todo(description);
        manager.addTask(task);
        storage.saveTasks(manager.getTasks());
        return ui.showAddMessage(task, manager.getTasks().size());
    }
}
