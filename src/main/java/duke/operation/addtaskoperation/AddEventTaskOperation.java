package duke.operation.addtaskoperation;

import java.time.LocalDateTime;

import duke.list.TaskList;
import duke.task.Event;

/**
 * Represents the operation that adds <code>Events</code>.
 */
public class AddEventTaskOperation extends AddTaskOperation {
    private final LocalDateTime time;

    /**
     * Constructor method.
     *
     * @param description the description of the <code>Event</code>.
     * @param time the time when the <code>Event</code> occurs.
     * @param taskList the <code>TaskList</code> that <code>Event</code> is to be added into.
     */
    public AddEventTaskOperation(String description, LocalDateTime time, TaskList taskList) {
        super(description, taskList);
        this.time = time;
    }

    /**
     * Creates the associated <code>Event</code>.
     *
     * @return an uncompleted <code>Event</code>.
     */
    @Override
    public Event createTask() {
        return Event.createEvent(description, time);
    }
}
