package duke.exception;

/**
 * This exception is thrown when an incomplete task creation command is sent.
 * @deprecated Use {@link InvalidCommandException}
 */
@Deprecated
public class EmptyTaskException extends Exception {
    private final String taskType;

    public EmptyTaskException(String taskType) {
        this.taskType = taskType;
    }

    @Override
    public String toString() {
        return "You can't add a non-existent " + taskType + ", silly!";
    }
}
