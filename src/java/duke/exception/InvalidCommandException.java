package duke.exception;

public class InvalidCommandException extends Exception {
    @Override
    public String toString() {
        return "I do not know what you mean, care to try again?";
    }
}
