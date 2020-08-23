package duke.operation;

import java.time.LocalDateTime;

import duke.task.TaskList;
import duke.task.Event;

/**
 * Represents the operation that adds Event objects.
 */
public class AddEventOperation extends AddOperation {
    private final LocalDateTime time;

    /**
     * Constructor method.
     * @param description the description of the Event.
     * @param time the time when the Event occurs.
     * @param taskList the TaskList that Event is to be added into.
     */
    public AddEventOperation(String description, LocalDateTime time, TaskList taskList) {
        super(description, taskList);
        this.time = time;
    }

    /**
     * Creates the associated Event.
     * @return an uncompleted Event.
     */
    @Override
    public Event createTask() {
        return Event.createEvent(this.description, this.time);
    }
}
