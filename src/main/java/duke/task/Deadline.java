package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDateTime timeBy;

    /**
     * @param desc A Constructor for the Deadline object representing a task with a deadline.
     * @param timeBy The time at which the event is due.
     * @throws DateTimeParseException
     */
    public Deadline(String desc, String timeBy) throws DateTimeParseException {
        super(desc);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        this.timeBy = LocalDateTime.parse(timeBy, format);
    }

    @Override
    public String toString() {
        String sign = isDone ? "✓" : "✗";
        return "[D][" + sign + "] " + description + " (by:" + timeBy + ")";
    }

    @Override
    public TaskType getType() {
        return TaskType.DEADLINE;
    }

    @Override
    public String getDate() {
        return timeBy.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
    }

    public String getDelimiter() {
        return "/by";
    }
}
