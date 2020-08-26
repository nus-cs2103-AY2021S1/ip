import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadline extends Task {
    private static final String SAVE_STRING = "DEADLINE|%s|%s|%s";
    private final LocalDate deadline;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deadline d = (Deadline) o;
        return super.equals(d) && this.deadline.equals(d.deadline);
    }
}
