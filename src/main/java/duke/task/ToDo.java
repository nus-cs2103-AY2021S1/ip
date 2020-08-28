package duke.task;

import duke.Task;

public class ToDo extends Task {

    public ToDo(String taskName) {
        super(taskName);
    }

    public String toString() {
        return String.format("[T][%s] %s", getStatusIcon(), taskName);
    }
}
