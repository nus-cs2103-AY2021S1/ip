package duke.task;

import java.time.LocalDateTime;

public class Deadline extends Task {

    private static final String DEADLINE_SYMBOL = "[D]";

    public Deadline(String description, LocalDateTime deadline) {
        super(description, TaskType.DEADLINE, "-", deadline, false);
    }

    public Deadline(String description, boolean isDone, String timeFrame, LocalDateTime dateTime) {
        super(description, TaskType.DEADLINE, timeFrame, dateTime, isDone);
    }

    @Override
    public String toString() {
        return DEADLINE_SYMBOL + super.toString() + " (by: " + getTime() + ")";
    }
}
