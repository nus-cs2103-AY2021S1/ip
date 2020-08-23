package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime by;
    private static final DateTimeFormatter FormatDateTime = DateTimeFormatter.ofPattern("EEEE, dd MMM yyyy, h:mma");

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = LocalDateTime.parse(by);
    }
    public String formatStyling() {
        return String.format("deadline,%s%s", by, super.formatStyling());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(FormatDateTime) + ")";
    }
}
