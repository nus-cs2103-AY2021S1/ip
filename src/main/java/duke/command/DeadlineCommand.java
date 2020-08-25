package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.UI;
import duke.task.Deadline;
import duke.task.TaskList;

import java.io.IOException;

public class DeadlineCommand extends Command {
    
    private final String argument;

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
     * @throws IOException If there are issues with writing to storage.
     */
    @Override
    public void execute(Storage storage, TaskList taskList, UI ui) throws DukeException, IOException {
        Deadline newDeadline = Deadline.createNewDeadline(argument);
        storage.writeLineToStorage(newDeadline.generateStorageString());
        ui.printToConsole(taskList.addTaskToList(newDeadline));
    }
}
