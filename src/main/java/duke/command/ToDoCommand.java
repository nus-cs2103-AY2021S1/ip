package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.UI;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * Encapsulates data and methods specific to the ToDo command.
 */
public class ToDoCommand extends Command {

    private final String argument;

    /**
     * Creates a new ToDo command.
     *
     * @param argument Argument keyed in by user in the ToDo command.
     */
    public ToDoCommand(String argument) {
        this.argument = argument;
    }

    /**
     * Creates a new ToDo task, updates storage and prints the action to console.
     *
     * @param storage Storage object pointing to the file path where the data is stored.
     * @param taskList Task list that the task needs to be added to.
     * @param ui UI object for the instance of Duke.
     * @throws DukeException If the argument for creating a new ToDo task is invalid.
     */
    @Override
    public void execute(Storage storage, TaskList taskList, UI ui) throws DukeException {

        assert ui != null : "UI cannot be null";
        assert storage != null : "Storage cannot be null";
        assert taskList != null : "Task list cannot be null";

        ToDo newTodo = ToDo.createNewToDo(argument);
        storage.writeLineToStorage(newTodo.generateStorageString());
        ui.printToConsole(taskList.addTaskToList(newTodo));
    }
}
