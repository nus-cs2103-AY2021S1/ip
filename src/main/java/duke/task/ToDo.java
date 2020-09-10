package duke.task;

import duke.Task;

import java.time.LocalDate;


/**
 * Represents the simplest form of {@code Task}
 * that only contains a name.
 */
public class ToDo extends Task {

    public ToDo(String taskName) {
        super(taskName);
    }

    @Override
    public String getDate() {
        return "";
    }

    @Override
    public void setDate(LocalDate taskDate) {
        // No dates to set here – method body is empty.
    }

    public String toString() {
        return String.format("[T][%s] %s", getStatusIcon(), taskName);
    }
}
