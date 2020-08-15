package operation;

import exception.DukeException;
import task.TaskStorage;
import task.Event;

public class AddEventOperation extends AddOperation {
    public AddEventOperation(String[] commands, TaskStorage taskStorage) {
        super(commands, taskStorage);
    }

    @Override
    public Event createTask() throws DukeException {
        return Event.createEvent(this.commands);
    }
}
