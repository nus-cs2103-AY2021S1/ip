package duke.commands;

import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UI;

/**
 * Represents a Todo command when the user wants to add
 * a new todo item.
 */
public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";

    /**
     * Creates an instance of a todo command with the appropriate description.
     *
     * @param commandBody Description of todo item.
     */
    public TodoCommand(String commandBody) {
        super(commandBody, false);
    }

    /**
     * Executes the command.
     * Creates a new todo item and adds it to the list of tasks.
     *
     * @param taskList List of tasks currently being tracked.
     * @param ui User interface object.
     * @param storage Storage object.
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        Task task = new ToDo(commandDescription);
        taskList.addToList(task);
        ui.displayAddedTask(task, taskList.getListSize());
    }
}
