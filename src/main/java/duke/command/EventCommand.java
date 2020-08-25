package main.java.duke.command;

import main.java.duke.DukeException;
import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.Ui;
import main.java.duke.task.Event;

/**
 * Represents a <code>Command</code> telling Duke to create a new <code>Event</code>.
 */
public class EventCommand implements Command {
    String command;

    public EventCommand(String command) {
        this.command = command;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Event event = Event.of(this.command);
        taskList.add(event);
        storage.appendToFile(event);
        ui.showTaskAdded(event);
    }

    public boolean isDone() {
        return false;
    }
}
