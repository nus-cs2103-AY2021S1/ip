package focus.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Represents task with a type of Deadline. */
public class Deadline extends Task {
    /** Represents the deadline of Deadline task. */
    private final LocalDateTime by;

    /**
     * Creates Deadline with user provided description and deadline.
     *
     * @param description Description of Deadline.
     * @param by Deadline of Deadline task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Converts Deadline to string form to be saved in user's files.
     *
     * @return String format of Deadline task.
     */
    @Override
    public String taskToText() {
        return "D|" + super.completed + "|" + super.taskDescription + "|" + by;
    }

    /**
     * Gets LocalDateTime of Deadline task.
     *
     * @return LocalDateTime by.
     */
    @Override
    public LocalDateTime getDeadline() {
        return this.by;
    }

    /**
     * Returns string format of Deadline.
     *
     * @return A string representation of Deadline.
     */
    @Override
    public String toString() {
        String pattern = "E, d MMM yyyy, h.mm a";
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern(pattern)) + ")";
    }
}
