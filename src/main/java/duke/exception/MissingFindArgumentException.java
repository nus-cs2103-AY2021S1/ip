package duke.exception;

public class MissingFindArgumentException extends Exception {
    public MissingFindArgumentException() {
        super("\uD83D\uDE41 OOPS! You have to add a keyword after find.");
    }
    @Override
    public String toString() {
        return super.getMessage();
    }
}
