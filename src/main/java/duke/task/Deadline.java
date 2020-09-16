package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents deadline task.
 */
public class Deadline extends Task {

    protected LocalDateTime dateTime;
    protected String originalDate;

    /**
     * Class constructor with no extra arguments.
     * @param description String description of deadline task.
     * @param dateTime String date of deadline task.
     */
    public Deadline(String description, String dateTime) {
        super(description);
        this.dateTime = LocalDateTime.parse(dateTime,
                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        originalDate = dateTime;
    }

    /**
     * Class constructor with extra boolean argument.
     * @param description String description of deadline task.
     * @param isDone Boolean representing whether task is done.
     * @param dateTime String date of deadline task.
     */
    public Deadline(String description, boolean isDone, String dateTime) {
        super(description, isDone);
        this.dateTime = LocalDateTime.parse(dateTime,
                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        originalDate = dateTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + dateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy, "
                + "hh:mm a")) + ")";
    }

    @Override
    public String write() {
        return "\ndeadline," + super.write() + "," + originalDate;
    }
}
