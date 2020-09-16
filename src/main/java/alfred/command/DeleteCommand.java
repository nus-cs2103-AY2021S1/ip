package alfred.command;

import alfred.AlfredException;
import alfred.Storage;
import alfred.UI;
import alfred.task.TaskList;

/**
 * Encapsulates data and methods specific to the Delete command.
 */
public class DeleteCommand extends Command {

    private final int argument;

    /**
     * Creates a new instance of the Delete command task.
     * @param argument Task ID of the task that needs to be deleted.
     */
    public DeleteCommand(int argument) {
        this.argument = argument;
    }

    /**
     * Deletes a task, updates storage and prints the action to console.
     *
     * @param storage Storage object pointing to the file path where the data is stored.
     * @param taskList Task list with the task that needs to be deleted.
     * @param ui UI object for the instance of Alfred.
     * @throws AlfredException If the argument for deleting a task is invalid.
     */
    @Override
    public void execute(Storage storage, TaskList taskList, UI ui) throws AlfredException {

        assert ui != null : "UI cannot be null";
        assert storage != null : "Storage cannot be null";
        assert taskList != null : "Task list cannot be null";

        ui.printToConsole(taskList.deleteTask(argument, storage));
    }
}
