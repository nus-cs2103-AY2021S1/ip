package duke;

/**
 * Represents a deadline inherited from task.
 * A <code>Deadline</code> object corresponds to
 * a task associated a date as deadline
 */

public class Deadline extends Task {
    /** Constructor.
     *  Initialises message.
     *  @param message deadline message
     */
    Deadline(String message) {
        super(message);
    }

    /** Constructor.
     *  Initialises message, isDone.
     *  @param message deadline message
     *  @param isDone status of deadline task
     */
    Deadline(String message, boolean isDone) {
        super(message, isDone);
    }

    /**
     * Returns the single character string
     * denoting task type.
     * @return "D" denoting deadline
     */
    @Override
    public String getPureTypeLetter() {
        return "D";
    }

    /**
     * Returns the task type notation with square brackets.
     * @return "[D]" denoting deadline
     */
    @Override
    public String getTypeLetter() {
        return "[D]";
    }

    /**
     * Returns the print message of this deadline.
     * @return string of form: [D] + [done/undone notation] + message
     */
    @Override
    public String getPrintMessage() {
        return Converter.by(getMessage());
    }

    /**
     * Returns the message of this deadline.
     * @return message
     */
    @Override
    public String getStoreMessage() {
        return getMessage();
    }
}
