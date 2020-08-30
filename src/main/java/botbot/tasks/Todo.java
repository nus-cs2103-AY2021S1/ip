package botbot.tasks;

/**
 * Represents a to-do with a description and completion status.
 */
public class Todo extends Task {
    public static final char TYPE_CODE = 'T';

    /**
     * Creates a to-do.
     *
     * @param command Command to create to-do.
     */
    public Todo(String command) {
        super(TYPE_CODE, extractNameFromCommand(command));
    }

    /**
     * Creates a to-do.
     *
     * @param description Description of to-do.
     * @param isDone Completion status of to-do.
     */
    public Todo(String description, boolean isDone) {
        super(TYPE_CODE, description, isDone);
    }

    private static String extractNameFromCommand(String command) {
        return command.substring(5);
    }

    /**
     * Returns the time of the to-do.
     *
     * @return Null.
     */
    @Override
    public String getAt() {
        return null;
    }

    /**
     * Returns the deadline of the to-do.
     *
     * @return Null.
     */
    @Override
    public String getBy() {
        return null;
    }

    @Override
    public String toString() {
        return String.format("[%c] [%s] %s", TYPE_CODE, getStatusIcon(), description);
    }
}