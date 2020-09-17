package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline. A deadline task has a description and the deadline of the task.
 */
public class Deadline extends Task {
    private static final String TEXTFILE_DATE_FORMAT = "yyyy-MM-dd HH:mm";
    private static final String OUTPUT_DATE_FORMAT = "MMM d yyyy hh:mm a";
    private LocalDateTime by;

    /**
     * Creates a deadline task that is not done.
     *
     * @param description Description of the task.
     * @param by duke.tasks.Deadline of the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Creates a deadline task, specifying if it is done.
     *
     * @param description Description of the task.
     * @param by duke.tasks.Deadline of the task.
     * @param isDone True to show task is done, False to show task is not done.
     */
    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    public LocalDateTime getBy() {
        return this.by;
    }

    /**
     * Marks a deadline task as done.
     *
     * @return A deadline task that is done.
     */
    @Override
    public Deadline markAsDone() {
        Deadline doneDeadline = new Deadline(this.getDescription(), this.by, true);
        return doneDeadline;
    }

    @Override
    public String toTxtFileFormat() {
        return "D" + super.toTxtFileFormat() + " | "
                + this.by.format(DateTimeFormatter.ofPattern(TEXTFILE_DATE_FORMAT));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT)) + ")";
    }
}
