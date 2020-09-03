package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    LocalDateTime timeBy;

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
