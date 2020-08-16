package operation;

import exception.DukeException;
import task.TaskStorage;
import task.Deadline;

public class AddDeadlineOperation extends AddOperation {
    private final String deadline;

    public AddDeadlineOperation(String description, String deadline, TaskStorage taskStorage) {
        super(description, taskStorage);
        this.deadline = deadline;
    }

    @Override
    public Deadline createTask() {
        return Deadline.createDeadline(this.description, this.deadline);
    }
}
