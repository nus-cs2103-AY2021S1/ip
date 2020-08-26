package duke.exception;

public class DeleteOutOfRangeException extends Exception {
    public DeleteOutOfRangeException() {
        super("\uD83D\uDE41 OOPS! Such a task does not exist.");
    }
    @Override
    public String toString() {
        return super.getMessage();
    }
}
