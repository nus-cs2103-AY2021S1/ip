package duke.exception;

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
