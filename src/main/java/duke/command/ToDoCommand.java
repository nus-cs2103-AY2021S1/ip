package duke.command;

import duke.Storage;
import duke.Ui;

import duke.exception.DukeException;
import duke.exception.InvalidTaskException;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Represents a command to add a new todo task to the user's list of tasks.
 */
public class ToDoCommand extends Command {

    /** Parsed commands containing details of the todo task. */
    private final String[] parsedCommand;

    /**
     * Creates and initialises a new ToDoCommand object.
     *
     * @param parsedCommand String array that contains the todo task information.
     */
    public ToDoCommand(String[] parsedCommand) {
        this.parsedCommand = parsedCommand;
    }

    /**
     * Creates a new todo task, adds it to the user's list of tasks
     * and saves it into the designated file that stores the user's tasks.
     *
     * @param tasks List of tasks which the new todo task will be added into.
     * @param ui Ui object created for the Duke object.
     * @param storage Storage object used by the Duke object for file operations.
     * @return String containing the reply for successful creation of ToDo task.
     * @throws DukeException If the todo task cannot be created due to invalid inputs.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

        String todoInfo = this.retrieveTodoInfo();
        Task toAdd = new Todo(todoInfo.trim());
        tasks.addTask(toAdd);
        storage.saveToFile(tasks);
        return ui.printNewTask(toAdd, tasks.getListSize());
    }

    /**
     * Retrieves the description of the todo task.
     *
     * @return String containing the todo description.
     * @throws InvalidTaskException If the todo description is missing.
     */
    public String retrieveTodoInfo() throws DukeException {
        String todoInfo;
        if (this.parsedCommand.length == 0) {
            String err = "Your todo task description is empty. The task cannot be created.";
            throw new InvalidTaskException(err);
        } else {
            todoInfo = this.parsedCommand[1];
        }
        return todoInfo;
    }

    /**
     * Indicates if the DukeBot session has ended.
     *
     * @return False since the DukeBot session has not been terminated.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
