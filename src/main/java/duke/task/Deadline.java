package duke.task;

import duke.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * Represents a type of {@code Task} that has a date
 * by which it should be finished.
 */
public class Deadline extends Task {
    protected LocalDate taskDate;

    public Deadline(String taskName, LocalDate taskDate) {
        super(taskName);
        this.taskDate = taskDate;
    }

    @Override
    public String getDate() {
        return taskDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    @Override
    public void setDate(LocalDate taskDate) {
        this.taskDate = taskDate;
    }

    public String toString() {
        return String.format("[D][%s] %s (by: %s)", getStatusIcon(), taskName, taskDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
