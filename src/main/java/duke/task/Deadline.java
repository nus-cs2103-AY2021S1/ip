package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    /** Deadline of a task */
    protected LocalDateTime by;

    /**
     * Creates a task with description and deadline.
     *
     * @param description Description of the task.
     * @param by Deadline of the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Creates a task with status, description and deadline.
     * @param isDone Status of the task.
     * @param description Description of the task.
     * @param by Deadline of the task.
     */
    public Deadline(boolean isDone,String description,LocalDateTime by) {
        super(description);
        this.by = by;
        this.isDone = isDone;
    }

    @Override
    public String toFileStringFormat() {
        return String.format("D / %d / %s / %s",isDone ? 1 : 0, this.desciption,this.by);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

}
