package duke.operation;

import java.time.LocalDateTime;

import duke.task.TaskList;
import duke.task.Deadline;

/**
 * Represents the operation that adds Deadline objects.
 */
public class AddDeadlineOperation extends AddOperation {
    private final LocalDateTime dateTime;

    /**
     * Constructor method.
     * @param description the description of the Deadline.
     * @param dateTime the datetime when the Deadline occurs.
     * @param taskList the TaskList that Deadline is to be added into.
     */
    public AddDeadlineOperation(String description, LocalDateTime dateTime, TaskList taskList) {
        super(description, taskList);
        this.dateTime = dateTime;
    }

    /**
     * Creates the associated Deadline.
     * @return an uncompleted Deadline.
     */
    @Override
    public Deadline createTask() {
        return Deadline.createDeadline(this.description, this.dateTime);
    }
}
