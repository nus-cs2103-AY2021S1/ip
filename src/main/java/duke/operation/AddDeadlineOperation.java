package duke.operation;

import java.time.LocalDateTime;

import duke.task.TaskList;
import duke.task.Deadline;

public class AddDeadlineOperation extends AddOperation {
    private final LocalDateTime dateTime;

    public AddDeadlineOperation(String description, LocalDateTime dateTime, TaskList taskList) {
        super(description, taskList);
        this.dateTime = dateTime;
    }

    @Override
    public Deadline createTask() {
        return Deadline.createDeadline(this.description, this.dateTime);
    }
}
