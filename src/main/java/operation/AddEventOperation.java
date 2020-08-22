package operation;

import java.util.Date;

import task.TaskList;
import task.Event;

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
