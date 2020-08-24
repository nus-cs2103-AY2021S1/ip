package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;

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
