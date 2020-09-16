package duke;

/**
 * Represents a todo inherited from task.
 * A <code>Deadline</code> object corresponds to
 * a simple task without any constraints on dates
 */
public class Todo extends Task {
    /** Constructor.
     *  Initialises message.
     * @param message todo message
     */
    public Todo(final String message) {
        super(message);
    }

    /** Constructor.
     *  Initialises message, isDone.
     * @param message todo message
     * @param isDone todo task state
     */
    Todo(final String message, final boolean isDone) {
        super(message, isDone);
    }

    /**
     * Returns the single character string denoting task type.
     * @return "T" denoting todo
     */
    @Override
    public String getPureTypeLetter() {
        return "T";
    }

    /**
     * Returns the task type notation with square brackets.
     * @return "[T]" denoting todo
     */
    @Override
    public String getTypeLetter() {
        return "[T]";
    }

    /**
     * Returns the print message of this deadline.
     * @return string of form: [T] + [done/undone notation] + message
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
