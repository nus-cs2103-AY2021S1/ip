package operation;

import task.TaskStorage;
import task.Event;

public class AddEventOperation extends AddOperation {
    private final String time;

    public AddEventOperation(String description, String time, TaskStorage taskStorage) {
        super(description, taskStorage);
        this.time = time;
    }

    @Override
    public Event createTask() {
        return Event.createEvent(this.description, this.time);
    }
}
