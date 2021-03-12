package duke.command;

import duke.exception.DukeException;
import duke.logic.Storage;
import duke.task.TaskManager;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * Represents a command to add todos.
 */
public class TodoCommand extends Command {

    /**
     * The Todo task to be stored.
     */
    private final Todo todo;

    /**
     * Constructs a command that adds a todo.
     *
     * @param description The description of the task.
     */
    public TodoCommand(String description) {
        todo = new Todo(description);
    }

    @Override
    public String execute(TaskManager manager, Ui ui, Storage storage) throws DukeException {
        manager.addTask(todo);
        storage.saveTasks(manager.getTasks());
        return ui.showAddMessage(todo, manager.getTasks().size());
    }
}
