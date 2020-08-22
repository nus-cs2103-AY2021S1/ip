package operation;

import java.util.Date;

import task.TaskList;
import task.Deadline;

public class AddDeadlineOperation extends AddOperation {
    private final Date deadline;

    public AddDeadlineOperation(String description, Date deadline, TaskList taskList) {
        super(description, taskList);
        this.deadline = deadline;
    }

    @Override
    public Deadline createTask() {
        return Deadline.createDeadline(this.description, this.deadline);
    }
}
