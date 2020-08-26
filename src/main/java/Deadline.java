import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * A Deadline is a Task, with the addition of a deadline, signifying when it has to be completed before.
 *
 * @author jingyenloh
 */
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

    /**
     * Serializes the Deadline into a String that is easy to parse. The String takes the form:
     * <code>DEADLINE|isDone|taskName|deadLine</code>.
     * The deadLine is formatted into yyyy-mm-dd, where yyyy is the year, mm- is the month, and dd is the day.
     * @return A formatted String ready for saving into a file
     */
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
