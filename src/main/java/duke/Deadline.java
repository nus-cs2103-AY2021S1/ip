package duke;

/**
 * Represents a deadline inherited from task.
 * A <code>Deadline</code> object corresponds to
 * a task associated a date as deadline
 */
public class Deadline extends Task {
    Deadline(final String message) {
        super(message);
    }

    Deadline(final String message, final boolean isDone) {
        super(message, isDone);
    }

    /**
     * Returns the single character string
     * denoting task type.
     * In this case, "D" denoting deadline
     */
    @Override
    public String getPureTypeLetter() {
        return "D";
    }

    /**
     * Returns the task type notation with square brackets.
     * In this case, "[D]" denoting deadline
     */
    @Override
    public String getTypeLetter() {
        return "[D]";
    }

    /**
     * Returns the print message of this deadline.
     * Contains [D] + [done/undone notation] + message
     */
    @Override
    public String getPrintMessage() {
        return Converter.by(getMessage());
    }

    /**
     * Returns the message of this deadline.
     */
    @Override
    public String getStoreMessage() {
        return getMessage();
    }
}
