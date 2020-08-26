package duke.task;

import duke.exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class is a deadline type of task. Users are able to store the due date and time.
 */
public class Deadline extends Task {
    private LocalDateTime taskBy;

    /**
     * Constructs a Deadline-type task with a description and due date/time.
     *
     * @param desc The description of this task.
     * @param by The due date/time of this task.
     */
    public Deadline(String desc, LocalDateTime by) {
        super(desc);
        this.taskBy = by;

        if (this.desc.isBlank() || this.taskBy == null) {
            throw new DukeException("The description or date of \"deadline\" cannot be empty");
        }
    }

    /**
     * {@inheritDoc}
     */
    public String getSaveToFileString() {
        return "D`" + super.getSaveToFileString() + "`" +
                taskBy.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                taskBy.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")));
    }
}
