package operation;

import task.TaskList;
import task.Event;

import java.util.Date;

public class AddEventOperation extends AddOperation {
    private final Date time;

    public AddEventOperation(String description, Date time, TaskList taskList) {
        super(description, taskList);
        this.time = time;
    }

    @Override
    public Event createTask() {
        return Event.createEvent(this.description, this.time);
    }
}
