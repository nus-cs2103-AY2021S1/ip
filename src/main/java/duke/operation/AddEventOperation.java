package duke.operation;

import java.time.LocalDateTime;

import duke.task.TaskList;
import duke.task.Event;

public class AddEventOperation extends AddOperation {
    private final LocalDateTime time;

    public AddEventOperation(String description, LocalDateTime time, TaskList taskList) {
        super(description, taskList);
        this.time = time;
    }

    @Override
    public Event createTask() {
        return Event.createEvent(this.description, this.time);
    }
}
