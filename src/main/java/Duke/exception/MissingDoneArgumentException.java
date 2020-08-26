package duke.exception;

public class MissingDoneArgumentException extends Exception {
    public MissingDoneArgumentException() {
        super("\uD83D\uDE41 OOPS! You have to specify which task you want to mark as done.");
    }
    @Override
    public String toString() {
        return super.getMessage();
    }
}
