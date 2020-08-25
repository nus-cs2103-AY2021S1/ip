package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDateTime durationFormatted;

    public Deadline(String task, String duration) {
        super(task, Tasktype.DEADLINE, duration);
    }

    public Deadline(String task, String duration, boolean isDone) {
        super(task, Tasktype.EVENT, duration, isDone);
        this.durationFormatted = LocalDateTime.parse(duration, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)",
                super.toString(), durationFormatted.format(DateTimeFormatter.ofPattern("d MMM yyyy, hh:mm a")));
    }

}
