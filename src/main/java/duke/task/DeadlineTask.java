package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    private LocalDate by;
    static final String TASK_TYPE = "D";

    public DeadlineTask(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public DeadlineTask(String description, LocalDate by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s)", TASK_TYPE, getStatusIcon(), description,
                by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }

    public String toDBString() {
        return TASK_TYPE + "~" + super.toDBString() + "~" + by.toString();
    }
}
