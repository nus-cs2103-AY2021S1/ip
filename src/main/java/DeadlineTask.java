import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class DeadlineTask extends Task {
    LocalDate deadline;
    String deadlineString;

    public DeadlineTask(String desc, String deadlineParse) {
        super(desc);
        try {
            deadline = LocalDate.parse(deadlineParse);
            deadlineString = String.format("%s %d %d",
                    deadline.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH),
                    deadline.getDayOfMonth(),
                    deadline.getYear());

        } catch (Exception e) {
            // Not parseable
            deadlineString = deadlineParse;
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadlineString + ")";
    }

    @Override
    public String toSaveString() {
        return String.format("D @@ %d @@ %s @@ %s",
                isDone ? 1 : 0, desc, deadlineString);
    }
}
