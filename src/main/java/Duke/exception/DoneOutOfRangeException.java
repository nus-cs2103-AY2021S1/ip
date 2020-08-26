package duke.exception;

public class DoneOutOfRangeException extends Exception {
    public DoneOutOfRangeException() {
        super("\uD83D\uDE41 OOPS! Such a task does not exist.");
    }
    @Override
    public String toString() {
        return super.getMessage();
    }
}
