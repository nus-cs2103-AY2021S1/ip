package duke.task;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.Storage;

public class Deadline extends Task {
    private LocalDate deadline;

    /**
     * Creates a new Deadline object that represents a task with a due date.
     * @param description information about the task
     * @param deadline date by which the task is expected to be completed
     */
    public Deadline(String description, LocalDate deadline) {
        super(description, "D");
        this.deadline = deadline;
    }

    @Override
    public String getSaveFormat() {
        return super.getSaveFormat() + Storage.LINE + deadline;
    }

    @Override
    public String toString() {
        return super.toString()
               + String.format(" (by: %s)", deadline.format(
                        DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
