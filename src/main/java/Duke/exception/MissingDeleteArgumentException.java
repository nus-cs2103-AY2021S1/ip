package duke.exception;

public class MissingDeleteArgumentException extends Exception {
    public MissingDeleteArgumentException() {
        super("\uD83D\uDE41 OOPS! You have to specify which task you want to delete.");
    }
    @Override
    public String toString() {
        return super.getMessage();
    }
}
