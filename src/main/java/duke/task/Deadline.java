package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime deadline;

    public Deadline(String name, boolean done, LocalDateTime deadline) {
        super(name, done);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String doneSymbol = isDone() ? "✓" : "✗";
        return String.format("[D][%s] %s (by: %s)", doneSymbol, getName(),
                deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")));
    }

    @Override
    public String toSaveString() {
        return String.format("D|%d|%s|%s", isDone() ? 1 : 0, getName(), deadline);
    }

}
