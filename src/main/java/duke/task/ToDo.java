package duke.task;

import duke.Task;


/**
 * Represents the simplest form of <code>Task</code>
 * that only contains a name.
 */
public class ToDo extends Task {

    public ToDo(String taskName) {
        super(taskName);
    }

    public String toString() {
        return String.format("[T][%s] %s", getStatusIcon(), taskName);
    }
}
