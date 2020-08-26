import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class DeadlineTask extends Task {
    private final LocalDate deadline;
    private static final String SAVE_STRING = "DEADLINE|%s|%s|%s";

    public DeadlineTask(String taskName, LocalDate deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    public DeadlineTask(boolean isDone, String taskName, LocalDate deadline) {
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
