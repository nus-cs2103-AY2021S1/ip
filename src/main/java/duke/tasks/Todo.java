package duke.tasks;

import duke.DukeException;

public class Todo extends Task {

    public Todo(String taskName) throws DukeException {
        super(taskName);
    }

    @Override
    public String toString() {
        return "[TODO] " + super.toString();
    }
}
