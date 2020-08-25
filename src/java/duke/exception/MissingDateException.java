package duke.exception;

public class MissingDateException extends Exception {
    @Override
    public String toString() {
        return "Hey, you need to tell me the date for this.";
    }
}
