package duke.exception;

public class InvalidIndexException extends Exception {
    @Override
    public String toString() {
        return "Fool, there is no task associated with this number!";
    }
}
