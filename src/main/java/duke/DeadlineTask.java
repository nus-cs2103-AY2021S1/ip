package duke;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Represents a deadline task with a deadline.
 */
public class DeadlineTask extends Task {
    LocalDate deadline;
    String deadlineString;

    /**
     * Initialise description to be desc and deadline to be deadlineParse. Will attempt to parse deadlineParse.
     * If it is in the format yyyy-mm-dd, it will be stored as local date, for nicer string representation later.
     *
     * @param desc Description of the task.
     * @param deadlineParse The deadline of the task.
     */
    public DeadlineTask(String desc, String deadlineParse) {
        super(desc);
        try {
            deadline = LocalDate.parse(deadlineParse);
            deadlineString = String.format("%s %d %d",
                    deadline.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH),
                    deadline.getDayOfMonth(),
                    deadline.getYear());

        } catch (Exception e) {
            // Not parsable
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
                isDone ? 1 : 0, desc, deadline == null ? deadlineString : deadline.toString());
    }

    @Override
    public boolean contains(String s) {
        return desc.contains(s) || deadlineString.contains(s);
    }
}
