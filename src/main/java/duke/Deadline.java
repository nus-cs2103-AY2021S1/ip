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

    @Override
    public String getPureTypeLetter() {
        return "D";
    }

    @Override
    public String getTypeLetter() {
        return "[D]";
    }

    @Override
    public String getPrintMessage() {
        return Converter.by(getMessage());
    }

    @Override
    public String getStoreMessage() {
        return getMessage();
    }
}
