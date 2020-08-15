package operation;

import exception.DukeException;
import task.TaskStorage;
import task.Deadline;

public class AddDeadlineOperation extends AddOperation {
    public AddDeadlineOperation(String[] commands, TaskStorage taskStorage) {
        super(commands, taskStorage);
    }

    @Override
    public Deadline createTask() throws DukeException {
        return Deadline.createDeadline(this.commands);
    }
}
