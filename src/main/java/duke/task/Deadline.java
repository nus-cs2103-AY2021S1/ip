package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents deadline task.
 */
public class Deadline extends Task {

    protected LocalDateTime by;
    protected String originalDate;

    /**
     * Class constructor with no extra arguments.
     * @param description String description of deadline task.
     * @param by String date of deadline task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by,
                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        originalDate = by;
    }

    /**
     * Class constructor with extra boolean argument.
     * @param description String description of deadline task.
     * @param isDone Boolean representing whether task is done.
     * @param by String date of deadline task.
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = LocalDateTime.parse(by,
                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        originalDate = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("d MMM yyyy, " +
                "hh:mm a")) + ")";
    }

    @Override
    public String write() {
        return "\ndeadline," + super.write() + "," + originalDate;
    }
}