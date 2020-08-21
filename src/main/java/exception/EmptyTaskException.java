package exception;

public class EmptyTaskException extends Exception {
    String taskType;

    public EmptyTaskException(String taskType) {
        this.taskType = taskType;
    }

    @Override
    public String toString() {
        String lines = "____________________\n";
        return lines + "You can't add a non-existent "
                + taskType + ", silly!\n"
                + lines;
    }
}
