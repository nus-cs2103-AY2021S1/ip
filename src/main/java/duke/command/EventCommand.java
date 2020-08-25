package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.UI;
import duke.task.Event;
import duke.task.TaskList;

import java.io.IOException;

public class EventCommand extends Command {
    
    private final String argument;

    public EventCommand(String argument) {
        this.argument = argument;
    }

    /**
     * Creates a new Event task, updates storage and prints the action to console.
     *
     * @param storage Storage object pointing to the file path where the data is stored.
     * @param taskList Task list that the task needs to be added to.
     * @param ui UI object for the instance of Duke.
     * @throws DukeException If the arguments for creating a new Event task are invalid. 
     * @throws IOException If there are issues with writing to storage.
     */
    @Override
    public void execute(Storage storage, TaskList taskList, UI ui) throws DukeException, IOException {
        Event newEvent = Event.createNewEvent(argument);
        storage.writeLineToStorage(newEvent.generateStorageString());
        ui.printToConsole(taskList.addTaskToList(newEvent));
    }
}
