package duke.operation.addtaskoperation;

import java.time.LocalDateTime;

import duke.list.TaskList;
import duke.task.Deadline;

/**
 * Represents the operation that adds <code>Deadlines</code>.
 */
public class AddDeadlineTaskOperation extends AddTaskOperation {
    private final LocalDateTime dateTime;

    /**
     * Constructor method.
     *
     * @param description the description of the <code>Deadline</code>.
     * @param dateTime the datetime when the <code>Deadline</code> occurs.
     * @param taskList the <code>TaskList</code> that <code>Deadline</code> is to be added into.
     */
    public AddDeadlineTaskOperation(String description, LocalDateTime dateTime, TaskList taskList) {
        super(description, taskList);
        this.dateTime = dateTime;
    }

    /**
     * Creates the associated <code>Deadline</code>.
     *
     * @return an uncompleted <code>Deadline</code>.
     */
    @Override
    public Deadline createTask() {
        return Deadline.createDeadline(description, dateTime);
    }
}
