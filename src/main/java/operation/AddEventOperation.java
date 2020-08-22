package operation;

import task.TaskList;
import task.Event;

public class AddEventOperation extends AddOperation {
    private final String time;

    public AddEventOperation(String description, String time, TaskList taskList) {
        super(description, taskList);
        this.time = time;
    }

    @Override
    public Event createTask() {
        return Event.createEvent(this.description, this.time);
    }
}
