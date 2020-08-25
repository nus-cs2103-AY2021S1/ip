package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.UI;
import duke.task.TaskList;

import java.io.IOException;

public class DeleteCommand extends Command {
    
    private final int argument;

    public DeleteCommand(int argument) {
        this.argument = argument;
    }

    /**
     * Deletes a task, updates storage and prints the action to console.
     *
     * @param storage Storage object pointing to the file path where the data is stored.
     * @param taskList Task list with the task that needs to be deleted.
     * @param ui UI object for the instance of Duke.
     * @throws DukeException If the argument for deleting a task is invalid. 
     * @throws IOException If there are issues with writing to storage.
     */
    @Override
    public void execute(Storage storage, TaskList taskList, UI ui) throws DukeException, IOException {
        ui.printToConsole(taskList.deleteTask(argument, storage));
    }
}
