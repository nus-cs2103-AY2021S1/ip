package duke.command;

import java.io.IOException;

import duke.DukeException;
import duke.Storage;
import duke.UI;
import duke.task.TaskList;

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
     * @param ui UI object for the instance of Duke.
     * @throws DukeException If the argument for marking a task as done is invalid.
     * @throws IOException If there are issues with writing to storage.
     */
    @Override
    public void execute(Storage storage, TaskList taskList, UI ui) throws DukeException, IOException {
        ui.printToConsole(taskList.markTaskAsDone(argument, storage));
    }
}
