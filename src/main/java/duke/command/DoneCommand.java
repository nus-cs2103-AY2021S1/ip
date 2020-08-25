package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.UI;
import duke.task.TaskList;

import java.io.IOException;

public class DoneCommand extends Command {
    
    private final int argument;

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
