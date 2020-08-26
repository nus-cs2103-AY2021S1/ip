import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadline extends Task {
    private final LocalDate deadline;
    private static final String SAVE_STRING = "DEADLINE|%s|%s|%s";

    public Deadline(String taskName, LocalDate deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    public Deadline(boolean isDone, String taskName, LocalDate deadline) {
        super(isDone, taskName);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format(
                "[D]%s (by: %s)",
                super.toString(),
                this.deadline.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
    }

    @Override
    public String toSaveString() {
        return String.format(SAVE_STRING, super.isDone, super.taskName, this.deadline);
    }
}
