package duke;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Represents a deadline task with a deadline.
 */
public class DeadlineTask extends Task {
    private LocalDate deadline;
    private String deadlineString;

    /**
     * Initialises description to be desc and deadline to be deadlineParse. Will attempt to parse deadlineParse.
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

    /**
     * Creates DeadlineTask with array as input. Array size should be exactly 2. First element will be used as
     * description and second element will be used as deadline.
     *
     * @param arguments The input array.
     * @return A DeadlineTask.
     */
    public static DeadlineTask of(String... arguments) {
        assert(arguments.length == 2);
        return new DeadlineTask(arguments[0], arguments[1]);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadlineString + ")";
    }

    private String toSavedStringDeadline() {
        return deadline == null ? deadlineString : deadline.toString();
    }

    @Override
    public String toSavedString() {
        return String.format("D @@ %s @@ %s @@ %s", toSavedStringIsDone(), desc, toSavedStringDeadline());
    }

    @Override
    public boolean contains(String s) {
        return desc.contains(s) || deadlineString.contains(s);
    }
}
