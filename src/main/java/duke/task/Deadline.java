package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description, TaskType.DEADLINE);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone, TaskType.DEADLINE);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    @Override
    public String getTime() {
        return by.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    @Override
    public String printTime() {
        return by.format(DateTimeFormatter.ofPattern("d MMM yyyy, hh:mm a"));
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), printTime());
    }

}
