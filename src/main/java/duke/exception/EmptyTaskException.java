package duke.exception;

/**
 * This exception is thrown when an incomplete task creation command is sent.
 */
public class EmptyTaskException extends Exception {
    private String taskType;

    public EmptyTaskException(String taskType) {
        this.taskType = taskType;
    }

    @Override
    public String toString() {
        return "You can't add a non-existent " + taskType + ", silly!";
    }
}
