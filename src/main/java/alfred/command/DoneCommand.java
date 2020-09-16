package alfred.command;

import alfred.AlfredException;
import alfred.Storage;
import alfred.UI;
import alfred.task.TaskList;

/**
 * Encapsulates data and methods specific to the Done command.
 */
public class DoneCommand extends Command {

    private final int argument;

    /**
     * Creates a new instance of the Done command task.
     * @param argument Task ID of the task that needs to be marked as done.
     */
    public DoneCommand(int argument) {
        this.argument = argument;
    }

    /**
     * Marks the task as done, updates storage and prints the action to console.
     *
     * @param storage Storage object pointing to the file path where the data is stored.
     * @param taskList Task list with the task that needs to be marked as done.
     * @param ui UI object for the instance of Alfred.
     * @throws AlfredException If the argument for marking a task as done is invalid.
     */
    @Override
    public void execute(Storage storage, TaskList taskList, UI ui) throws AlfredException {

        assert ui != null : "UI cannot be null";
        assert storage != null : "Storage cannot be null";
        assert taskList != null : "Task list cannot be null";

        ui.printToConsole(taskList.markTaskAsDone(argument, storage));
    }
}
