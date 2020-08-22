package operation;

import task.TaskList;
import task.Deadline;

public class AddDeadlineOperation extends AddOperation {
    private final String deadline;

    public AddDeadlineOperation(String description, String deadline, TaskList taskList) {
        super(description, taskList);
        this.deadline = deadline;
    }

    @Override
    public Deadline createTask() {
        return Deadline.createDeadline(this.description, this.deadline);
    }
}
