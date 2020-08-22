package duke.operation;

import java.time.LocalDate;

import duke.task.TaskList;
import duke.task.Event;

public class AddEventOperation extends AddOperation {
    private final LocalDate time;

    public AddEventOperation(String description, LocalDate time, TaskList taskList) {
        super(description, taskList);
        this.time = time;
    }

    @Override
    public Event createTask() {
        return Event.createEvent(this.description, this.time);
    }
}
