package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.UI;
import duke.task.Deadline;
import duke.task.TaskList;

/**
 * Encapsulates data and methods specific to the Deadline command.
 */
public class DeadlineCommand extends Command {

    private final String argument;

    /**
     * Creates a new instance of the Deadline command task.
     * @param argument Argument passed in by the user.
     */
    public DeadlineCommand(String argument) {
        this.argument = argument;
    }

    /**
     * Creates a new Deadline task, updates storage and prints the action to console.
     *
     * @param storage Storage object pointing to the file path where the data is stored.
     * @param taskList Task list that the task needs to be added to.
     * @param ui UI object for the instance of Duke.
     * @throws DukeException If the arguments for creating a new Deadline task are invalid.
     */
    @Override
    public void execute(Storage storage, TaskList taskList, UI ui) throws DukeException {

        assert ui != null : "UI cannot be null";
        assert storage != null : "Storage cannot be null";
        assert taskList != null : "Task list cannot be null";

        Deadline newDeadline = Deadline.createNewDeadline(argument);
        storage.writeLineToStorage(newDeadline.generateStorageString());
        ui.printToConsole(taskList.addTaskToList(newDeadline));
    }
}
