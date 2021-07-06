package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class Deadline extends Task {

    /**
     * Creates a task with description and deadline.
     *
     * @param description Description of the task.
     * @param by Deadline of the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.time = Optional.of(by);
    }

    /**
     * Creates a task with status, description and deadline.
     * @param isDone Status of the task.
     * @param description Description of the task.
     * @param by Deadline of the task.
     */
    public Deadline(boolean isDone, String description, LocalDateTime by) {
        super(description);
        this.time = Optional.of(by);
        this.isDone = isDone;
    }

    @Override
    public String toFileStringFormat() {
        assert time.isPresent();
        return String.format("D / %d / %s / %s", isDone ? 1 : 0, this.desciption, this.time.get());
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        assert time.isPresent();
        return "[D]" + super.toString() + " (by: " + time.get().format(formatter) + ")";
    }

}
