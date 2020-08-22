package duke.operation;

import java.time.LocalDate;

import duke.task.TaskList;
import duke.task.Deadline;

public class AddDeadlineOperation extends AddOperation {
    private final LocalDate deadline;

    public AddDeadlineOperation(String description, LocalDate deadline, TaskList taskList) {
        super(description, taskList);
        this.deadline = deadline;
    }

    @Override
    public Deadline createTask() {
        return Deadline.createDeadline(this.description, this.deadline);
    }
}
