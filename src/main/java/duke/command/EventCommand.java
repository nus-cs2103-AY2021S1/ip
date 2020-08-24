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

    @Override
    public void execute(Storage storage, TaskList taskList, UI ui) throws DukeException, IOException {
        Event newEvent = Event.createNewEvent(argument);
        storage.writeLineToStorage(newEvent.generateStorageString());
        ui.printToConsole(taskList.addTaskToList(newEvent));
    }
}
