package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * To instantiate a new Deadline Task Object.
     * @param description  Description of deadline.
     * @param deadline  Deadline of task.
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy @ h:mma"))
                + ")";
    }
    @Override
    public String convertToData() {
        return "D | " + (isDone ? 1 : 0) + " | " + description + " | " + deadline.toString();
    }
}
