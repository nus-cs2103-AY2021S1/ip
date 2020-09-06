package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TaskDeadline extends Task {

    private LocalDateTime deadline;

    public TaskDeadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String[] getSaveData() {
        return new String[] {"D", isDone ? "1" : "0", description,
                String.format("%s", deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd Hmm"))) };
    }

    @Override
    public String toString()
    {
        return String.format("[D][%s] %s (by: %s)", getStatusIcon(),
                description, deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy',' Hmm'hrs'")));
    }
}
