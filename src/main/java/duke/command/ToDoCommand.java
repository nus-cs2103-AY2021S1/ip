package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Represents a command to add a new todo task to the user's list of tasks.
 */
public class ToDoCommand extends Command {

    /** String containing the description of the todo task. */
    private final String todoDescription;

    /**
     * Creates and initialises a new ToDoCommand object.
     *
     * @param todoDescription String containing the description of the todo task.
     */
    public ToDoCommand(String todoDescription) {
        this.todoDescription = todoDescription;
        assert !todoDescription.isBlank() : "todo description cannot be empty";
    }

    /**
     * Creates a new todo task, adds it to the user's list of tasks
     * and saves it into the designated file that stores the user's tasks.
     *
     * @param tasks List of tasks which the new todo task will be added into.
     * @param ui Ui object created for the Duke object to handle user interactions.
     * @param storage Storage object used by the Duke object for file operations.
     * @return String containing the reply for successful creation of the todo task.
     * @throws DukeException If the todo task could not be saved.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task todo = new Todo(this.todoDescription);
        tasks.addTask(todo);
        storage.saveToFile(tasks);
        return ui.showNewTask(todo, tasks.getListSize());
    }

    /**
     * Indicates if the Duke session has ended.
     *
     * @return False since the Duke session has not been terminated.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
