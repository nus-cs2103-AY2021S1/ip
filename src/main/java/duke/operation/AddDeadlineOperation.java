package duke.operation;

import java.time.LocalDateTime;

import duke.task.TaskList;
import duke.task.Deadline;

public class AddDeadlineOperation extends AddOperation {
    private final LocalDateTime deadline;

    public AddDeadlineOperation(String description, LocalDateTime deadline, TaskList taskList) {
        super(description, taskList);
        this.deadline = deadline;
    }

    @Override
    public Deadline createTask() {
        return Deadline.createDeadline(this.description, this.deadline);
    }
}
